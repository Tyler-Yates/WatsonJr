package com.aqa.candidates;

import com.aqa.kb.KnowledgeBase;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Class to create and search a Lucene index for a given knowledge base.
 */
public class LuceneSearch {
    private static final String ID_FIELD = "id";
    private static final String TEXT_FIELD = "text";
    private static final int MAX_RESULTS = 10;

    private final KnowledgeBase knowledgeBase;

    private Directory index = null;

    /**
     * Creates a new Lucene index for the given knowledge base. Subsequent changes to the knowledge base will not be
     * reflected in this instance. If you change the knowledge base you need to create a new instance with the modified
     * knowledge base.
     *
     * @param knowledgeBase the given knowledge base
     */
    public LuceneSearch(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    /**
     * Executes the given search query and returns the ranked results.
     *
     * @param searchQuery the given search query
     * @return the ranked results
     * @throws IOException    if there was an error accessing the index
     * @throws ParseException if there was an error parsing the query
     */
    public RankedCandidates search(String searchQuery) throws IOException, ParseException {
        final ScoreDoc[] scoreDocs = searchIndex(searchQuery);
        final RankedCandidates.Builder rankedCandidatesBuilder = new RankedCandidates.Builder(searchQuery);
        for (final ScoreDoc scoreDoc : scoreDocs) {
            final Document luceneDocument = getDocument(scoreDoc.doc);
            final com.aqa.kb.Document knowledgeBaseDocument = knowledgeBase.getDocument(
                    Integer.parseInt(luceneDocument.get(ID_FIELD)));
            rankedCandidatesBuilder.addCandidate(knowledgeBaseDocument, scoreDoc.score);
        }
        return rankedCandidatesBuilder.build();
    }

    /**
     * Creates a Lucene index for the knowledge base.
     *
     * @throws IOException
     */
    private void createIndex() throws IOException {
        // Create the index in memory
        index = new RAMDirectory();

        // Configure and create the index writer
        final Analyzer analyzer = new StandardAnalyzer();
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        final IndexWriter indexWriter = new IndexWriter(index, indexWriterConfig);

        // Add each document from the knowledge base into the index
        for (final com.aqa.kb.Document document : knowledgeBase.getDocuments()) {
            final Document doc = new Document();
            doc.add(new StringField(ID_FIELD, Integer.toString(document.getId()), Field.Store.YES));
            doc.add(new TextField(TEXT_FIELD, document.getText(), Field.Store.YES));
            indexWriter.addDocument(doc);
        }

        // Close the writer
        indexWriter.close();
    }

    /**
     * Searches the given Lucene index using the given search string.
     *
     * @param searchString the given search string
     * @return a scored array of documents
     * @throws IOException    if there was an error accessing the index
     * @throws ParseException if there was an error parsing the search string
     */
    private ScoreDoc[] searchIndex(String searchString) throws IOException, ParseException {
        System.out.println("Searching for '" + searchString + "'");

        if (index == null) {
            createIndex();
        }

        final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));
        final QueryParser parser = new QueryParser(TEXT_FIELD, new StandardAnalyzer());
        final Query query = parser.parse(searchString);
        final TopDocs results = searcher.search(query, MAX_RESULTS);
        final ScoreDoc[] scoreDocs = results.scoreDocs;
        System.out.println("Found " + scoreDocs.length + " documents");
        return scoreDocs;
    }

    /**
     * Returns the document with the given ID, if one exists, from the given Lucene index.
     *
     * @param id the given ID
     * @return the document
     * @throws IOException if there was an error accessing the index
     */
    private Document getDocument(int id) throws IOException {
        if (index == null) {
            createIndex();
        }

        final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));
        return searcher.getIndexReader().document(id);
    }
}

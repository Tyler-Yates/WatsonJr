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
 * Class to create and search a Lucene index.
 */
public class LuceneSearch {
    public static final String ID_FIELD = "id";
    public static final String TEXT_FIELD = "text";

    private static final int MAX_RESULTS = 10;

    /**
     * Creates a Lucene index from the given {@link KnowledgeBase}.
     *
     * @param knowledgeBase the given knowledge base
     * @return the created Lucene index
     * @throws IOException if there is an error creating the index
     */
    public static Directory createIndex(KnowledgeBase knowledgeBase) throws IOException {
        // Configure and create the index writer
        final Analyzer analyzer = new StandardAnalyzer();
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        final Directory indexDirectory = new RAMDirectory();
        final IndexWriter indexWriter = new IndexWriter(indexDirectory, indexWriterConfig);

        // Add each document from the knowledge base into the index
        for (final com.aqa.kb.Document document : knowledgeBase.getDocuments()) {
            final Document doc = new Document();
            doc.add(new StringField(ID_FIELD, Integer.toString(document.getId()), Field.Store.YES));
            doc.add(new TextField(TEXT_FIELD, document.getText(), Field.Store.YES));
            indexWriter.addDocument(doc);
        }

        // Close the writer and return the new Lucene index
        indexWriter.close();
        return indexDirectory;
    }

    /**
     * Searches the given Lucene index using the given search string.
     *
     * @param index        the given Lucene index
     * @param searchString the given search string
     * @return a scored array of documents
     * @throws IOException    if there was an error accessing the index
     * @throws ParseException if there was an error parsing the search string
     */
    public static ScoreDoc[] searchIndex(Directory index, String searchString) throws IOException, ParseException {
        System.out.println("Searching for '" + searchString + "'");

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
     * @param index the given Lucene index
     * @param id    the given ID
     * @return the document
     * @throws IOException if there was an error accessing the index
     */
    public static Document getDocument(Directory index, int id) throws IOException {
        final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));
        return searcher.getIndexReader().document(id);
    }
}

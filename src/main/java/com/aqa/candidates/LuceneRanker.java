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
 * Class that uses the Lucene search engine to answer questions. You will need to implement the {@link
 * #answerQuestion(KnowledgeBase, String)} method.
 * <p/>
 * You should only access the text of the documents (using {@link Document}) from the knowledge base. You will
 * not need to use the information from CoreNLP in the document because Lucene will handle the natural language
 * processing for you.
 * <p/>
 * The following links are useful for finding out how to use Lucene:
 * <p/>
 * <a href="http://oak.cs.ucla.edu/cs144/projects/lucene/">http://oak.cs.ucla.edu/cs144/projects/lucene/</a>
 * <p/>
 * <a href="https://lucene.apache.org/core/6_0_0/core/overview-summary.html">https://lucene.apache
 * .org/core/6_0_0/core/overview-summary.html</a>
 */
public class LuceneRanker implements Ranker {
    private static final String ID_FIELD = "id";
    private static final String TEXT_FIELD = "text";
    private static final int MAX_RESULTS = 10;

    @Override
    public RankedCandidates answerQuestion(KnowledgeBase knowledgeBase, String question) throws IOException, ParseException {
        // TODO Use Lucene to create an in-memory index of the knowledge base and execute the question as a search query

        // Use RAMDirectory from org.apache.lucene.store to create the index in memory
        final Directory index = new RAMDirectory();

        // Use IndexWriter from org.apache.lucene.index to add documents to the index

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

        // Use Query from org.apache.lucene.search to turn the String question into a Lucene query
        // Use IndexSearcher from org.apache.lucene.search to execute the query
        final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));
        final QueryParser parser = new QueryParser(TEXT_FIELD, new StandardAnalyzer());
        final Query query = parser.parse(question);
        final TopDocs results = searcher.search(query, MAX_RESULTS);
        final ScoreDoc[] scoreDocs = results.scoreDocs;

        RankedCandidates.Builder builder = new RankedCandidates.Builder(question);
        for (final ScoreDoc scoreDoc : scoreDocs) {
            builder.addCandidate(knowledgeBase.getDocument(Integer.parseInt(getDocument(index, scoreDoc.doc).getField(ID_FIELD).stringValue())), scoreDoc.score);
        }

        return builder.build();
    }

    private Document getDocument(Directory index, int id) throws IOException {
        final IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));
        return searcher.getIndexReader().document(id);
    }
}

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class tast {
@Test
    public void tt() throws Exception {

    QueryParser title = new QueryParser("title", new IKAnalyzer());
    Query parse = title.parse("谷歌");
    serth(parse);
    }
@Test
    public void t1() throws IOException {
        TermQuery termQuery = new TermQuery(new Term("title", "加盟"));
        serth(termQuery);
    }

    @Test
    public void t2() throws Exception {
        WildcardQuery termQuery = new WildcardQuery(new Term("title", "*父"));
        serth(termQuery);
    }
//模糊查询
    @Test
    public void t3() throws Exception {
        FuzzyQuery termQuery = new FuzzyQuery(new Term("title", "faceboov"));
        serth(termQuery);
    }


    /*
     * 测试：数值范围查询
     * 注意：数值范围查询，可以用来对非String类型的ID进行精确的查找
     */
    @Test
    public void testNumericRangeQuery() throws Exception{
        // 数值范围查询对象，参数：字段名称，最小值、最大值、是否包含最小值、是否包含最大值
        Query query = NumericRangeQuery.newLongRange("id", 2L, 2L, true, true);
        serth(query);
    }

    /*
     * 布尔查询：
     * 	布尔查询本身没有查询条件，可以把其它查询通过逻辑运算进行组合！
     * 交集(and)：Occur.MUST + Occur.MUST
     * 并集(or)：Occur.SHOULD + Occur.SHOULD
     * 非(not)：Occur.MUST_NOT
     */
    @Test
    public void testBooleanQuery() throws Exception{

        Query query1 = NumericRangeQuery.newLongRange("id", 1L, 3L, true, true);
        Query query2 = NumericRangeQuery.newLongRange("id", 2L, 4L, true, true);
        // 创建布尔查询的对象
        BooleanQuery query = new BooleanQuery();
        // 组合其它查询
        query.add(query1, BooleanClause.Occur.MUST_NOT);
        query.add(query2, BooleanClause.Occur.SHOULD);

        serth(query);
    }

    private void serth(Query parse) throws IOException {
        Directory directory= FSDirectory.open(new File("e://index/iii"));
        DirectoryReader open = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(open);

        TopDocs search = indexSearcher.search(parse, 10);
        ScoreDoc[] scoreDocs= search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId= scoreDoc.doc;
            Document doc = indexSearcher.doc(docId);
            System.out.println(doc.get("id"));
            System.out.println(doc.get("title"));
        }
    }
}

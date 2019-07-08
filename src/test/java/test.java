import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.document.Field.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class test {
    @Test
    public void tt() throws Exception {
        //创建文档对象
       Document document=  new Document();
       document.add(new StringField("id","1", Store.YES));
       document.add(new TextField("title","谷歌地图之父跳槽跑路传智播客屌爆了", Store.YES));
       document.add(new StoredField("url","http://www.jd.com/431"));
        //索引目录
        Directory directory= FSDirectory.open(new File("e://index/iii"));
//index相当于数据域
//type相当于表结构
//document相当于一条记录
        //配置参数
       // Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        // 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);

        // 把文档交给IndexWriter
        indexWriter.addDocument(document);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }



    @Test
    public void t() throws Exception {
        //创建文档对象
        Collection list=new ArrayList();
        Document document1 = new Document();
        document1.add(new LongField("id", 1, Store.YES));
        document1.add(new TextField("title", "谷歌地图之父跳槽facebook", Store.YES));
        list.add(document1);
        // 创建文档对象
        Document document2 = new Document();
        document2.add(new LongField("id", 2, Store.YES));
        document2.add(new TextField("title", "谷歌地图之父加盟FaceBook", Store.YES));
        list.add(document2);
        // 创建文档对象
        Document document3 = new Document();
        document3.add(new LongField("id", 3, Store.YES));
        document3.add(new TextField("title", "谷歌地图创始人拉斯离开谷歌加盟Facebook", Store.YES));
        list.add(document3);
        // 创建文档对象
        Document document4 = new Document();
        document4.add(new LongField("id", 4, Store.YES));
        document4.add(new TextField("title", "谷歌地图之父跳槽Facebook与Wave项目取消有关", Store.YES));
        list.add(document4);
        // 创建文档对象
        Document document5 = new Document();
        document5.add(new LongField("id", 5, Store.YES));
        document5.add(new TextField("title", "谷歌地图之父拉斯加盟社交网站Facebook", Store.YES));
        list.add(document5);
        //索引目录
        Directory directory= FSDirectory.open(new File("e://index/iii"));
//index相当于数据域
//type相当于表结构
//document相当于一条记录
        //配置参数
        // Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        // 索引写出工具的配置对象
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);

        // 把文档交给IndexWriter
        indexWriter.addDocuments(list);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }
}

package top.javahai.learnelasticsearch;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import top.javahai.learnelasticsearch.bean.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2020/12/20 - 13:17
 **/
@SpringBootTest
public class Doc_ElasticSearchTest {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 添加文档
     */
    @Test
    void testAddDocument() throws IOException {
        User user = new User("李四", 20);
        IndexRequest request = new IndexRequest("test_index01");
        //规则 put /test_index01/_doc/1
        request.id("2")
                .timeout(TimeValue.timeValueSeconds(1))
                //将数据放入到请求
                .source(JSON.toJSONString(user), XContentType.JSON);
        //客户端发送请求，获取响应的结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        //CREATED
        System.out.println(indexResponse.status());
    }

    /**
     * 获取文档，判断文档是否存在
     */
    @Test
    void testIsExists() throws IOException{
        GetRequest getRequest = new GetRequest("test_index01", "1");
        //不获取返回的额_source的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        //指定返回的字段,默认为_source
        getRequest.storedFields("found");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
        System.out.println(response);
    }

    /**
     * 获取文档信息
     */
    @Test
    void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("test_index01", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println(response.getSourceAsString());

    }

    /**
     * 更新文档操作
     * @throws IOException
     */
    @Test
    void testUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("test_index01", "1");
        User user = new User("大名", 1);
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }
    /**
     * 删除文档操作
     */
    @Test
    void testDeleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("test_index01", "3");
        deleteRequest.timeout("1s");
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }
    /**
     * 批量插入文档
     */
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Ethan1",18));
        users.add(new User("Ethan2",18));
        users.add(new User("Ethan3",18));
        users.add(new User("Ethan4",18));
        users.add(new User("Ethan5",18));
        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("test_index01").id(""+(i+5)).source(JSON.toJSONString(users.get(i)),XContentType.JSON)
            );
        }
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.hasFailures());
    }
    /**
     * 复杂查询操作
     * SearchRequest 搜索请求
     * SearchSourceBuilder 条件构造
     * HighlightBuilder 构建高亮
     * TermQueryBuilder 精确查询
     * MatchAllQueryBuilder
     * ...
     */
    @Test
    void testSearch() throws IOException {
        SearchRequest request = new SearchRequest("test_index01");
        //构造搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //构造精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "Ethan1");
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response.getHits()));

    }



}

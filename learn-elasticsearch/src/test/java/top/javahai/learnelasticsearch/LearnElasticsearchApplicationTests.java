package top.javahai.learnelasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;

@SpringBootTest
class LearnElasticsearchApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    ElasticsearchRestTemplate restTemplate;

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    void testCreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test_index01");
        //客户端执行请求，indicesClient，请求后获得响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    /**
     * 获取索引
     */
    @Test
    void testGetIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test_index01");
        //判断索引是否存在
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
        if (exists){
            //获取索引
            GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
            System.out.println(getIndexResponse);
        }
    }

    /**
     * 删除索引
     */
    @Test
    void testDeleteIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("test_temp");
        client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("test_temp");
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(acknowledgedResponse.isAcknowledged());
    }

}

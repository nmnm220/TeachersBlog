package ru.blog.teachersblog.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.blog.teachersblog.dao.VKPostMapper;
import ru.blog.teachersblog.models.VKPost;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
public class VKInitializer {
    private final Integer GROUP_ID = 51304100;
    private final int APP_ID = 51537270;
    private final String GROUP_KEY = "vk1.a.vnHSJeROb-0UvZqh3en7VirnLuuHotW5GqjijAErDCL5oo0t5-Dv2iVl-qoKbcmMdGWZI_XD_5bFuzmeA99Fr_O1XXRftJBfwWQb3ne_vcL6FMUZVj63vR-_fj9HKKJyIKM8OENqfq5nNmEfUSsvlbXeMRzj3xOwR39aNayVBeY5zTkCS4EBYi1WgQc8HEELe350FKfigjEyeWxo_i9IJA";
    private final String SERVICE_KEY = "f3b3876df3b3876df3b3876da3f0a1e21bff3b3f3b3876d9066aee31bed62a3f6526be3";
    private final String DOMAIN = "club51304100";
    private final int OWNER_ID = -51304100;
    private final TransportClient transportClient = new HttpTransportClient();
    private final VkApiClient vk = new VkApiClient(transportClient);
    private final ServiceActor serviceActor = new ServiceActor(APP_ID, SERVICE_KEY);
    private final GroupActor actor = new GroupActor(GROUP_ID, GROUP_KEY);
    private List<VKPost> vkPosts = null;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VKInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void getPosts() {
        vkPosts = new ArrayList<>();
        List<com.vk.api.sdk.objects.wall.WallpostFull> wallPostFullList;
        try {
            GetResponse getResponse = vk.wall().get(serviceActor)
                    .ownerId(OWNER_ID)
                    .domain(DOMAIN)
                    .count(100)
                    .execute();
            wallPostFullList = getResponse.getItems();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }
        for (com.vk.api.sdk.objects.wall.WallpostFull wallpostFull : wallPostFullList) {
            JSONObject JSONPost = new JSONObject(wallpostFull);

            int postId = JSONPost.getInt("id");
            String postText = JSONPost.getString("text");
            long dateUnix = JSONPost.getInt("date") * 1000L;
            String hash = JSONPost.getString("hash");
            SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
            date.setTimeZone(TimeZone.getTimeZone("GMT+3"));
            String postDate = date.format(dateUnix);
            if (postText.length() > 0) {
                vkPosts.add(new VKPost(postId, postText, postDate, hash));
            }
        }
        addPostsToDB();
    }

    private void addPostsToDB() {
        List<VKPost> vkPostsFromDB = jdbcTemplate.query("SELECT * FROM VKposts", new VKPostMapper());
        for (VKPost vkPost : vkPosts) {
            boolean isNotInDB = true;
            for (VKPost vkPostDB : vkPostsFromDB)
                if (!vkPost.getHash().equals(vkPostDB.getHash())) {
                    isNotInDB = false;
                    break;
                }
            if (isNotInDB)
                jdbcTemplate.update("INSERT INTO VKposts values(?, ?, ?, ?)", vkPost.getId(), vkPost.getTitle(), vkPost.getText(), vkPost.getHash());
        }
    }

    public List<VKPost> getPostsFromDB() {
        return jdbcTemplate.query("SELECT * FROM VKposts order by id desc", new VKPostMapper());
    }
}

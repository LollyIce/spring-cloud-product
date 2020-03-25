package product.userClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import product.DTO.UserInfo;

import java.util.List;

@FeignClient(name = "user")
public interface UserClient {
    @PostMapping("/user/getUserByIds")
    public List<UserInfo> getUserByIds(@RequestBody List<String> ids);
}

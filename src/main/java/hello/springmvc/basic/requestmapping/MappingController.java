package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    // /hello-basic 과 /hello-basic/ 은 다른 요청이지만 /hello-basic 으로 매핑시켜줌
    // method 속성으로 HTTP 메서드 속성을 지정하지 않으면 메서드와 무관하게 호출(GET, POST, PUT, DELETE, PATCH 모두 허용)
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    // /hello-basic URL 호출이 오면 이 메서드가 실행되도록 매핑
    // 대부분의 속성을 배열로 제공하므로 다중 설정이 가능 {"/hello-basic", "/hello-go"}}
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    // 축약 애노테이션
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    // PathVariable 사용
    // 변수명이 같으면 생략 가능 @PathVariable("userId") String userId -> @PathVariable String userId
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId = {}", userId);
        return "ok";
    }

    // PathVariable 다중 사용
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId={}", userId, orderId);
        return "ok";
    }

    // 파라미터로 추가 매핑
    // URL 정보 && param 조건 일 때만 매핑
    /**
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    // 특정 헤더로 추가 매핑
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // 미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume
    // Content-Type 헤더 기반 추가 매핑 Media Type
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    // 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce
    // Accept 헤더 기반 Media Type
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}

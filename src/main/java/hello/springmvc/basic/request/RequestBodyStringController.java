package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    // InputStream(Reader) : HTTP 요청 메시지 바디의 내용 직접 조회
    // OutputStream(Reader) : HTTP 응답 메시지 바디의 내용 직접 출력
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity 요청에서 사용
     *  - 메시지 바디 정보 직접 조회
     *  - 요청 파라미터를 조회하는 기능과는 전혀 관계 없음 @RequestParam, @ModelAttribute
     * HttpEntity 응답에서 사용
     *  - 메시지 바디 정보 직접 반환
     *  - 헤더 정보 포함 가능
     *  - view 조회 X
     * */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

    // HttpEntity를 상속 받은 RequestEntity, ResponseEntity 사용 가능!
    // RequestEntity : HttpMethod, url 정보 추가, 요청에서 사용
    // ResponseEntity : Http 상태 코드 설정 가능, 응답에서 사용
    @PostMapping("/request-body-string-v3-1")
    public ResponseEntity<String> requestBodyStringV3_1(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    // @RequestBody : 요청 파라미터를 조회하는 @RequestParma, @ModelAttribute 와는 관련 없음!!!!
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}

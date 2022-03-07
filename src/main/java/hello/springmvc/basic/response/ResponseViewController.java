package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    // @ResponseBody가 없으면 response/hello로 뷰 리졸버가 실행되어 뷰를 찾고 렌더링
    // @ResponseBody가 있으면 뷰 리졸버를 실행하지 않고 HTTP 메시지 바디에 직접 response/hello 문자가 입력됨
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello") // template/response/hello 가 렌더링 됨
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    // @Controller를 사용하고, HttpServletResponse, OutputStream(Writer) 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면
    // 요청 URL을 참고해서 논리 뷰 이름을 사용
    // 명시성이 없고, 딱 맞는 경우가 많이 없어 권장하지 않음
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}

package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//
@Controller
public class MemberController {
    //private final MemberService memberService = new MemberService();
    //위의 방식 보다 컨테이너의 하나만 등록해서 사용
    private final MemberService memberService;
    //아래의 방식처럼 생성자를 만들어서 호출하면 MemberService를 딱 한 번 만든ㅁ
    //생성자의 Autowired는 memberService를 스프링 컨테이너에 있는 MemberSerivce를 가져와 연결을 시켜 준다.
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //join을 하면 알아서 세이브를 할 것임

        return "redirect:/";
    }
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}

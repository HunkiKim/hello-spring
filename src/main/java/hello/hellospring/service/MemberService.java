package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }

    /*
    -회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        //아래와 같이 바로 반환도 좋지 않음.
//        Optional<Member> result = memberRepository.findByName(member.getName());// 일단 이름 찾고
        validateDuplicateMember(member);
        // 옵셔널로 해서 ifPresent
        //get으로 꺼낼 수 있지만 좋지않음
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     전체 회원 조회
     */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    //상속받아서 가능한 구조
    @AfterEach // 메서드가 실행을 끝날때마다 실행해주는 콜백 메소드
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        //코냥 실행된다. 녹색불
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//Optional이라 + Test 코드라 get
        // 이제 검증 똑같은지 확인
//        System.out.println("result = " + (result == member));
        //글자로 보는건 쫌;
        //org.junit.jupiter.api
        Assertions.assertEquals(member,result);
        //org.assertj.core.api
        assertThat(member).isEqualTo(result);
        //import static을 사용하여 없앨 수 있음 But 윈도우는 왜 안되지?
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        //Shift + F6을 사용하면 아래 이름들 쭉 바꾸기 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        //Shift + F6을 사용하면 아래 이름들 쭉 바꾸기 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}

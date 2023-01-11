package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 스프링이 제공하는거 쓰자
@RequiredArgsConstructor // 알아서 파이널인 것만 가지고 생성자 만들어줌
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired 요즘 최신 경향임. 이러면 테스트 짤 때도 편함
    // 최신 스프링에선 생성자 하나만 있으면 알아서 빈으로 등록한다.
    // 롬복으로 얘도 없앨 수 있음.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional // 기본값은 리드온리 false임
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName()); // 실무에서는 유니크 제약으로 동시성 고려
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

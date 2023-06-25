package indigo.indigoproject.service;

import indigo.indigoproject.domain.Member;
import indigo.indigoproject.repository.MemberRepository;
import indigo.indigoproject.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public String join(Member member){
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        memberRepository.save(member);
        return member.getUserId();
    }
}

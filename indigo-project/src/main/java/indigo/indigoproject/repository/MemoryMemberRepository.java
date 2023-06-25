package indigo.indigoproject.repository;

import indigo.indigoproject.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<String, Member> store = new HashMap<>();
    public static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setUserId("" + ++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

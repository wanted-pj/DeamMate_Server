package com.wanted_server.Repository;

import com.wanted_server.Class.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonalRepository  {

    private final EntityManager em;

    public Long save(Personal personal) {
        em.persist(personal);
        return personal.getId();
    }

    public Personal findOne(Long id) {
        return em.find(Personal.class, id);
    }

    public List<Personal> findByStringId(String stringId) {
        return em.createQuery("select p from Personal p where p.stringId = :stringId", Personal.class)
                .setParameter("stringId", stringId)
                .getResultList();
    }

    public List<Personal> findByNickname(String nickname) {
        return em.createQuery("select p from Personal p where p.nickname = :nickname", Personal.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public List<Personal> findAll() {
        return em.createQuery("select p from Personal p", Personal.class)
                .getResultList();
    }

    public List<Personal> findBySchool(String school) {
        return em.createQuery("select p from Personal p where p.school = :school",
                Personal.class)
                .setParameter("school", school)
                .getResultList();
    }

    public List<Personal> findByMajor(String major) {
        return em.createQuery("select p from Personal p where p.major = :major",
                Personal.class)
                .setParameter("major", major)
                .getResultList();
    }

    public List<Personal> findByAddress(String address) {
        return em.createQuery("select p from Personal p where p.address = :address",
                Personal.class)
                .setParameter("address", address)
                .getResultList();
    }

    public Long delete(Long personalId) {
        Personal personal = findOne(personalId);
        if (personal == null) {
            System.out.println("존재 하지 않는 회원입니다.");
        } else{
            em.remove(personal);
            System.out.println("성공적으로 제거되었습니다.");
        }
        return personalId;
    }
}



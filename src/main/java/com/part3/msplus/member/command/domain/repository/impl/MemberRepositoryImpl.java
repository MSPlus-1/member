package com.part3.msplus.member.command.domain.repository.impl;

import com.part3.msplus.member.command.domain.repository.MemberCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class MemberRepositoryImpl implements MemberCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

}

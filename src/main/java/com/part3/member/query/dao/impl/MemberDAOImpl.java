package com.part3.member.query.dao.impl;

import com.part3.member.command.domain.entity.Member;
import com.part3.member.query.dao.MemberCustomDAO;
import com.part3.member.query.dao.MemberDAO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class MemberDAOImpl implements MemberCustomDAO {


}
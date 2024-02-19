package com.part3.msplus.oauth2.command.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StateRepository extends CrudRepository<State, UUID> {

}

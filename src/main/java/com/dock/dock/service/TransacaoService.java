package com.dock.dock.service;

import com.dock.dock.domain.entity.TransacaoEntity;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoService {

    List<TransacaoEntity> consultarExtrato(Integer numeroConta, LocalDate dataIncio, LocalDate dataFim);

    TransacaoEntity efetuarTransacao(TransacaoEntity transacaoEntity);
}

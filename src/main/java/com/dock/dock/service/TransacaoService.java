package com.dock.dock.service;

import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.domain.model.ExtratoModel;

import java.time.LocalDateTime;

public interface TransacaoService {

    ExtratoModel consultarExtrato(String cpf, LocalDateTime dataIncio, LocalDateTime dataFim);

    TransacaoEntity efetuarTransacao(TransacaoEntity transacaoEntity);
}

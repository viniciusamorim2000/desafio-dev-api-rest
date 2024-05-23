package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.domain.model.ExtratoModel;
import com.dock.dock.service.TransacaoService;

import java.time.LocalDateTime;

public class TransacaoServiceImpl implements TransacaoService {
    @Override
    public ExtratoModel consultarExtrato(String cpf, LocalDateTime dataIncio, LocalDateTime dataFim) {
        return null;
    }

    @Override
    public TransacaoEntity efetuarTransacao(TransacaoEntity transacaoEntity) {
        return null;
    }


}

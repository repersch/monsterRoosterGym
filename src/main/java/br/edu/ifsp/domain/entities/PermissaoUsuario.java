package br.edu.ifsp.domain.entities;

import java.util.EnumSet;

enum Permissao {

    AUTENTICAR,
    CRIAR_USUARIO,
    EDITAR_USUARIO,
    BUSCAR_USUARIO,
    CRIAR_EXERCICIO,
    EDITAR_EXERCICIO,
    EXCLUIR_EXERCICIO,
    BUSCAR_EXERCICIO,
    CRIAR_TREINO,
    EDITAR_TREINO,
    GERENCIAR_FICHA_TREINO,
    VISUALIZAR_FICHA_TREINO,
    GERAR_RELATORIO,
    BUSCAR_REGISTRO_TREINO,
    REGISTRAR_INICIO_TREINO,
    REGISTRAR_FINAL_TREINO,
    ALTERAR_CARGA_EXERCICIO

}

public class PermissaoUsuario {

    EnumSet<Permissao> permissoesInstrutor = EnumSet.of(
            Permissao.AUTENTICAR,
            Permissao.CRIAR_USUARIO,
            Permissao.BUSCAR_USUARIO,
            Permissao.EDITAR_USUARIO,
            Permissao.CRIAR_EXERCICIO,
            Permissao.BUSCAR_EXERCICIO,
            Permissao.EDITAR_EXERCICIO,
            Permissao.EXCLUIR_EXERCICIO,
            Permissao.CRIAR_TREINO,
            Permissao.EDITAR_TREINO,
            Permissao.GERENCIAR_FICHA_TREINO,
            Permissao.VISUALIZAR_FICHA_TREINO,
            Permissao.GERAR_RELATORIO
    );

    EnumSet<Permissao> permissoesAluno = EnumSet.of(
            Permissao.AUTENTICAR,
            Permissao.REGISTRAR_INICIO_TREINO,
            Permissao.REGISTRAR_FINAL_TREINO,
            Permissao.ALTERAR_CARGA_EXERCICIO,
            Permissao.BUSCAR_REGISTRO_TREINO
    );
}

package br.edu.ifsp.domain.usecases.aluno;

import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class BuscarAlunoUC {

    private AlunoDAO alunoDAO;

    public BuscarAlunoUC(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

        public Optional<Aluno> buscarPorId(Integer id) {
            if (id == null) {
                throw new IllegalArgumentException("Id não pode ser nulo.");
            }

            return alunoDAO.findById(id);
        }

       public Optional<Aluno> buscarPorCpf (String cpf) {
            if (Validator.nuloOuVazio(cpf)) {
                throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
            }

            return alunoDAO.findByAttribute("cpf", cpf);
       }

        public Optional<Aluno> buscarPorNome (String nome) {
            if (Validator.nuloOuVazio(nome)) {
                throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
            }

            return alunoDAO.findByAttribute("nome", nome);
        }

        public List<Aluno> buscarTodos() {
            return alunoDAO.findAll();
        }

}

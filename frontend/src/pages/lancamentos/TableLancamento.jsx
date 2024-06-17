import React from "react";
import currencyFormatter from "currency-formatter";
import { MdDelete,MdModeEdit,MdCancelPresentation  } from "react-icons/md";
import { HiCheck } from "react-icons/hi";


const LancamentoTable = ({
  lancamentos,
  alterarStatus,
  cancelarStatus,
  editAction,
  deleteAction,
}) => {
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">Descrição</th>
          <th scope="col">Valor</th>
          <th scope="col">Tipo</th>
          <th scope="col">Mês</th>
          <th scope="col">Situação</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        {
          Array.isArray(lancamentos) && lancamentos.map((lancamento) => (
            <tr key={lancamento.id}>
              <td>{lancamento.descricao}</td>
              <td>
                {currencyFormatter.format(lancamento.valor, {
                  locale: "pt-BR",
                })}
              </td>
              <td>{lancamento.tipo}</td>
              <td>{lancamento.mes}</td>
              <td>{lancamento.status}</td>
              <td>
                <button
                  className="btn btn-success"
                  title="Efetivar"
                  disabled={lancamento.status !== "PENDENTE"}
                  onClick={() => alterarStatus(lancamento, "EFETIVADO")}
                  type="button"
                >
                    <HiCheck/>
                </button>
                <button
                  className="btn btn-warning"
                  title="Cancelar"
                  disabled={lancamento.status !== "PENDENTE"}
                  onClick={() => cancelarStatus(lancamento, "CANCELADO")}
                  type="button"
                >
                  <MdCancelPresentation  />
                </button>
                <button
                  type="button"
                  title="Editar"
                  className="btn btn-primary"
                  onClick={() => editAction(lancamento.id)}
                >
                  <MdModeEdit />
                </button>
                <button
                  type="button"
                  title="Excluir"
                  className="btn btn-danger"
                  onClick={() => deleteAction(lancamento)}
                >
                  <MdDelete/>
                </button>
              </td>
            </tr>
          ))}
      </tbody>
    </table>
  );
};

export default LancamentoTable;

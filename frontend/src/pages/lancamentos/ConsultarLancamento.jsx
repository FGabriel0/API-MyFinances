import React, { useEffect, useState } from "react";
import Card from "../../components/Card";
import {
  listaLancamento,
  deleteLancamento,
  updateStatus,
} from "../../services/LancamentoService";
import { jwtDecode } from "jwt-decode";
import LancamentoTable from "./TableLancamento";
import NavBar from "../../components/NavBar";
import { mensagemErro, mensagemSucesso } from "../../components/toastr";


const ConsultarLancamento = () => {
  const [lancamentos, setLancamentos] = useState([]);
  const token = localStorage.getItem("token");
  const decode = jwtDecode(token);
  const usuario_id = decode.userId;

  useEffect(() => {
    if (!token) {
      mensagemErro("Usuário não autenticado!");
      return;
    }
    const fetchLancamentos = async () => {
      try {
        const response = await listaLancamento(usuario_id, token);
        setLancamentos(response.data.data);
      } catch (error) {
        console.error("Erro ao buscar lançamentos:", error);
      }
    };

    fetchLancamentos();
  }, [usuario_id,token]);

  const alterarStatus = async (lancamento) => {
    try {
        await updateStatus(lancamento.id,token)
        setLancamentos(status => status.filter(lancamentos => lancamentos.status == "PENDENTE"))
    } catch (error) {
        mensagemErro("Erro ao Efetivar",error)
    }
  };

  const editAction = (lancamentoId) => {
    // Lógica para editar o lançamento
  };

  const deleteAction =  async (lancamento) => {
    if (!token) {
        mensagemErro("Usuário não autenticado!");
        return;
      }
      try {
        await deleteLancamento(lancamento.id, token);
        setLancamentos(prevLancamentos => prevLancamentos.filter(lancamentos => lancamentos.id !== id));
        mensagemSucesso("Deletado com sucesso")
      } catch (error) {
        console.error("Erro ao deletar lançamento:", error);
      }
  };

  return (
    <div className="row">
        <NavBar/>
      <div className="col-md-8 offset-md-2">
        <div className="bs-docs-section">
          <Card title="Consulta Lançamentos">
            <LancamentoTable
              lancamentos={lancamentos}
              alterarStatus={alterarStatus}
              editAction={editAction}
              deleteAction={deleteAction}
            />
          </Card>
        </div>
      </div>
    </div>
  );
};

export default ConsultarLancamento;

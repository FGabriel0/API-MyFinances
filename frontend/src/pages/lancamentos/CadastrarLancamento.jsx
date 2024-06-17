import React, { useState } from "react";
import Card from "../../components/Card";
import FormGroup from "../../components/FormGroup";
import SelectMenu from "../../components/SelectMenu";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { mensagemSucesso,mensagemErro } from "../../components/toastr";
import { salvarLancamento } from "../../services/LancamentoService";
import NavBar from "../../components/NavBar";

const CadastrarLancamento = () => {
  const [descricao, setDescricao] = useState("");
  const [ano, setAno] = useState(0);
  const [mes, setMes] = useState("");
  const [valor, setValor] = useState(0);
  const [status, setStatus] = useState("");
  const [tipoLancamento, setTipoLancamento] = useState("");

  const meses = [
    { value: "1", label: "Janeiro" },
    { value: "2", label: "Fevereiro" },
    { value: "3", label: "Março" },
    { value: "4", label: "Abril" },
    { value: "5", label: "Maio" },
    { value: "6", label: "Junho" },
    { value: "7", label: "Julho" },
    { value: "8", label: "Agosto" },
    { value: "9", label: "Setembro" },
    { value: "10", label: "Outubro" },
    { value: "11", label: "Novembro" },
    { value: "12", label: "Dezembro" },
  ];
  const tipos = [
    {value: "",label:"Tipo do lançamento"},
    { value: "RECEITA", label: "RECEITA" },
    { value: "DESPENSA", label: "DESPENSA" },
  ];

  const handlerLancamento = async () => {

    const token = localStorage.getItem("token");
    if (!token) {
      mensagemErro("Usuário não autenticado!");
      return;
    }
    try {
      const decodedToken = jwtDecode(token);
      const usuario_id = decodedToken.userId;
      const lancamentoData = {
        descricao,
        ano,
        mes,
        usuario_id,
        valor,
        tipoLancamento,
      };

      await salvarLancamento(lancamentoData, token);
      mensagemSucesso("Lançamento cadastrado com sucesso!");

      // Limpar os campos após o sucesso
      setDescricao("");
      setAno("");
      setMes("");
      setValor("");
    } catch (error) {
      console.error("Erro ao salvar lançamento:", error);
      mensagemErro("Erro ao salvar lançamento.");
    }
  };
    



  return (
    <div>
        <NavBar/>
    <Card title="Cadastra Lançamento">
      <div className="row">
        <div className="col-md-12">
          <FormGroup id="inputDescricao" label="Descrição: *">
            <input
              id="inputDescricao"
              type="text"
              className="form-control"
              name="descricao"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
            />
          </FormGroup>
        </div>
      </div>
      <div className="row">
        <div className="col-md-6">
          <FormGroup id="inputAno" label="Ano: *">
            <input
              id="inputAno"
              type="text"
              name="ano"
              value={ano}
              onChange={(e) => setAno(e.target.value)}
              className="form-control"
            />
          </FormGroup>
        </div>
        <div className="col-md-6">
          <FormGroup id="inputMes" label="Mês: *">
            <SelectMenu
              id="inputMes"
              value={mes}
              onChange={(e) => setMes(e.target.value)}
              lista={meses}
              name="mes"
              className="form-control"
            />
          </FormGroup>
        </div>
      </div>
      <div className="row">
        <div className="col-md-4">
          <FormGroup id="inputValor" label="Valor: *">
            <input
              id="inputValor"
              type="text"
              name="valor"
              value={valor}
              onChange={(e) => setValor(e.target.value)}
              className="form-control"
            />
          </FormGroup>
        </div>

        <div className="col-md-4">
            <FormGroup id="inputTipo" label="Tipo: *">
              <SelectMenu
                id="inputTipo"
                lista={tipos}
                name="tipo"
                value={tipoLancamento}
                onChange={(e) => setTipoLancamento(e.target.value)}
                className="form-control"
              />
            </FormGroup>
          </div>

        <div className="col-md-4">
          <FormGroup id="inputStatus" label="Status: ">
            <input
              type="text"
              className="form-control"
              name="status"
              value={status}
              disabled
            />
          </FormGroup>
        </div>

        <button onClick={handlerLancamento} className="btn btn-success">
          <i className="pi pi-save"></i> Salvar
        </button>
      </div>
      {/* <div className="row">
         <div className="col-md-6" >
            { atualizando ? 
                (
                    <button onClick={atualizar} 
                            className="btn btn-success">
                            <i className="pi pi-refresh"></i> Atualizar
                    </button>
                ) : (
                    <button onClick={submit} 
                            className="btn btn-success">
                            <i className="pi pi-save"></i> Salvar
                    </button>
                )
            }
            <button onClick={e => navigate('/consulta-lancamentos')} 
                    className="btn btn-danger">
                    <i className="pi pi-times"></i>Cancelar
            </button>
        </div>
    </div> */}
    </Card>
    </div>
  );
};

export default CadastrarLancamento;

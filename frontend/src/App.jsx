import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Cadastra from "./pages/Cadastra";
import "toastr/build/toastr.min";
import "toastr/build/toastr.css";
import { isUserLoggedIn } from "./services/AuthService";
import NotFoundPage from "./pages/NotFoundPage";
import CadastrarLancamento from "./pages/lancamentos/CadastrarLancamento";
import ConsultarLancamento from "./pages/lancamentos/ConsultarLancamento";

function App() {
  function AuthenticatedRoute({ children }) {
    const isAuth = isUserLoggedIn();

    if (isAuth) {
      return children;
    }

    return <Navigate to="/" />;
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route
          path="/home"
          element={
            <AuthenticatedRoute>
              <Home />
            </AuthenticatedRoute>
          }
        />

        <Route
          path="/cadastra-lancamentos"
          element={
            <AuthenticatedRoute>
              <CadastrarLancamento />
            </AuthenticatedRoute>
          }
        />
        <Route
          path="/consultar-lancamentos"
          element={
            <AuthenticatedRoute>
              <ConsultarLancamento />
            </AuthenticatedRoute>
          }
        />
        <Route path="/cadastra" element={<Cadastra />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

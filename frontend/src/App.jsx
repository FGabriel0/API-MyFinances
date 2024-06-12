import { BrowserRouter,Routes,Route } from 'react-router-dom'
import Login from './pages/Login'
import Home from './pages/Home'
import Cadastra from './pages/Cadastra'
import 'toastr/build/toastr.min'
import 'toastr/build/toastr.css'


function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/home' element={<Home/>}/>
        <Route path='/cadastra' element={<Cadastra/>}/>
      </Routes>
    </BrowserRouter>
  
  )
}

export default App

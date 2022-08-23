// import logo from './logo.svg';
import './App.css';
import { NavBar } from './component/Nav-bar';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { HomePage } from './pages/home/HomePage';
import LoginPage from './pages/login/LoginPage';
import { RegisterPage } from './pages/register/RegisterPage';
import { ProfilePage } from './pages/profile/ProfilePage';
import { AdminPage } from './pages/admin/AdminPage';
import { NotFoundPage } from './pages/error/NotFoundPage';
import { UnauthorizedPage } from './pages/error/UnauthorizedPage';
import { AuthGuard } from './guards/AuthGuard';
import { Role } from './models/Role';

function App() {
  return (
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //     <p>Hello world</p>
    //   </header>
    // </div>

    
    <BrowserRouter>
      <NavBar />
      <div className="container">
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/home" element={<HomePage/>} />
          <Route path="/login" element={<LoginPage/>} />
          <Route path="/register" element={<RegisterPage/>} />
          <Route path="/profile" 
                      element={<AuthGuard roles={[Role.ADMIN, Role.USER]}>
                                    <ProfilePage/>
                              </AuthGuard>} />  
          <Route path="/admin" 
                      element={<AuthGuard roles={[Role.ADMIN]}>
                                    <AdminPage/>
                              </AuthGuard>} />
          <Route path="/404" element={<NotFoundPage/>} />
          <Route path="/401" element={<UnauthorizedPage/>} />
          <Route path="*" element={<NotFoundPage/>} />
        </Routes>
      </div>

    </BrowserRouter>
  );
}

export default App;

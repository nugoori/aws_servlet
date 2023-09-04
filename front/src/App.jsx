import { Route, Routes } from 'react-router-dom';
import './App.css';
import MainLayout from './components/MainLayout/MainLayout';
/** @jsxImportSource @emotion/react */
import { Global, css } from '@emotion/react';
import Signup from './pages/signup/Signup';
import Signin from './pages/signin/SignIn';
import Asynchronous from './pages/Asynchronous/Asynchronous';

const SCommon = css`
  * {
    box-sizing: border-box;
  }
`;

function App() {
  return (
    <>
      <Global styles={SCommon}/>
      <MainLayout >
        <Routes>
          <Route path='/'/>
          <Route path='/signin' element={ <Signin /> }/>
          <Route path='/signup' element={ <Signup /> }/>
          <Route path='/async' element={ <Asynchronous /> }/>
          
        </Routes>
      </MainLayout>
    </>
  );
}
export default App;

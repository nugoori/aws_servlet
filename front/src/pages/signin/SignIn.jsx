import axios from 'axios';
import React, { useState } from 'react';

function SignIn(props) {

    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: ""
    });

    const handleInputChange = (e) => {
        setSigninInput({
            ...signinInput,
            [e.target.name]: e.target.value
        })
    }

    const handleSigninClick = async () => {
        // get요청 시 data가 노출되기 때문에 노출되면 안되는 요청들은 body에 data가 담기는 post요청을 사용
        try {
            const response = await axios.post("http://localhost:8080/servlet_study_jhj/auth/signin", signinInput); 
            
            console.log(response);

            if(!response.data.token) {
                alert("로그인 정보가 잘못 되었습니다.");
                return;
            }
            
            localStorage.setItem("token", response.data?.token);
            alert("환영합니다.");
            

        } catch (error) {
            console.log(error);
        };
    }

    return (
        <>
            <h1>로그인</h1>
            <div><input type="text" name='username' onChange={handleInputChange} placeholder="username"/></div>
            <div><input type="password" name='password' onChange={handleInputChange} placeholder="password"/></div>
            <div><button onClick={handleSigninClick}>로그인</button></div>
        </>
    );
}

export default SignIn;


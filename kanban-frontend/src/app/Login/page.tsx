"use client";

import React from "react";
import { useState } from "react";

export default function Page(){

  const [formData, setFormData] = useState({
    email: "",
    password:""
  });

  const handleChange = (e: { target: { name: string; value: unknown ; }; }) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const redirectToSignUp = async () =>{
    try{
      const response = await fetch("http://localhost:8080/api/sign-up",{
        method: "GET",
        credentials:"include"
      });
      const redirectUrl = await response.text();
      window.location.href = redirectUrl;
    }catch( error){
      console.error("Redirection error", error);
    }
  };

  const loginRequest = async (e: { preventDefault: () => void; }) =>{
    e.preventDefault();
    try{
      const response = await fetch("http://localhost:8080/api/Login",{
        method: "POST",
        headers:{
          'Content-Type': 'application/json',
        },
        credentials: "include",
        body: JSON.stringify(formData)
      });
      if(response.ok){
        const data = await response.json();
        console.log("login succesful: ",data)
        alert("succesful login")
        setTimeout(()=>{
          window.location.href = "http://localhost:3000/Home"
        }, 1000);
      }
    }catch(e){
      console.error("error submitting the data: ", e)
    }

    console.log("submitted data, ", formData)
    setFormData({email: "", password: ""});


  }

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <div className="bg-white p-6 rounded-2xl shadow-md w-80">
        <h2 className="text-2xl font-semibold text-center mb-4">Login</h2>
        <form onSubmit={loginRequest}>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">Email</label>
            <input
              name="email"
              type="email"
              id="email"
              required
              value={formData.email}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter your email"
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">Password</label>
            <input
              id="password"
              name="password"
              type="password"
              required
              value={formData.password}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter your password"
              onChange={handleChange}
            />
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 m-2 text-white p-2 rounded-lg hover:bg-blue-600 transition"
          >
            Login
          </button>

        </form>
        <button
            type="button"
            onClick={redirectToSignUp}
            className="w-full bg-blue-500 m-2 text-white p-2 rounded-lg hover:bg-blue-600 transition"
          >
            Sign Up
          </button>
      </div>
    </div>
  );
};


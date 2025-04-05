"use client";

import React from "react";

export default function Page(){
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

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <div className="bg-white p-6 rounded-2xl shadow-md w-80">
        <h2 className="text-2xl font-semibold text-center mb-4">Login</h2>
        <form>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">Name</label>
            <input
              type="text"
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter your name"
            />
          </div>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">Email</label>
            <input
              type="email"
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter your email"
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


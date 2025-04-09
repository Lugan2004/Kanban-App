"use client";
import { useState } from "react";

export default function Page() {
  const [formData, setFormData] = useState({
    username: "",
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

  const handleSubmit =  async  (e: { preventDefault: () => void; }) => {
    e.preventDefault();

    try{
       const response = await fetch("http://localhost:8080/api/sign-up",{
        method: "POST",
        headers:{
          'Content-Type': 'application/json',
        },
        credentials: "include",
        body: JSON.stringify(formData)
       });
       
       if(response.ok){
        const data = await response.json();
        console.log("Registration successful: ", data);
        alert("succesful user creation") 
        setTimeout(()=>{
          window.location.href = "http://localhost:3000/Login";
        }, 1000)
        
       }else{
        const errorData = await response.text();
        console.error('Registration failed:', errorData);
        alert(errorData)
       }

    }catch(error){
      console.error("Error Submitting the data: ", error)
    }
    

    console.log("Submitted data:", formData);
    // Here you would typically send the data to your backend
    
    // Reset the form
    setFormData({ username: "", email: "" , password: ""});
  };

  return (
    <div className="flex min-h-screen flex-col items-center justify-center bg-gray-50 p-4">
      <div className="w-full max-w-md space-y-8 rounded-lg bg-white p-6 shadow-md">
        <div className="text-center">
          <h1 className="text-3xl font-bold text-gray-900">Sign Up</h1>
          <p className="mt-2 text-gray-600">Create your account</p>
        </div>
        
        <form onSubmit={handleSubmit} className="mt-8 space-y-6">
          <div className="space-y-4 rounded-md shadow-sm">
            <div>
              <label htmlFor="username" className="block text-sm font-medium text-gray-700">
                Username
              </label>
              <input
                id="username"
                name="username"
                type="text"
                required
                value={formData.username}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-blue-500"
                placeholder="Enter your username"
              />
            </div>
            
            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                Email
              </label>
              <input
                id="email"
                name="email"
                type="email"
                required
                value={formData.email}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-blue-500"
                placeholder="Enter your email"
              />
            </div>
            <div>
              <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                Password
              </label>
              <input
                id="password"
                name="password"
                type="password"
                required
                value={formData.password}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-blue-500"
                placeholder="Create password"
              />
            </div>
          </div>

          <div>
            <button
              type="submit"
              className="group relative flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-sm font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
            >
              Sign Up
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
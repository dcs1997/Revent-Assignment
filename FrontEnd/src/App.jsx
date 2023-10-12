import { useState } from 'react'
import './App.css'
import { Route,Routes } from 'react-router-dom'
import SignupLogin from './Pages/SignupLogin'
import Task from './Pages/Task'

function App() {


  return (
   <div className="App">
     <Routes>
      <Route path="/" element={<SignupLogin/>}/>
      <Route path="/task" element={<Task/>}/>
     </Routes>
   </div>
    )
}

export default App

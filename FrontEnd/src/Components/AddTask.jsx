import { useState } from "react";
import axios from "axios";
import "./AddTask.css";

function AddTask() {
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    status: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);

    try {
    let id = localStorage.getItem("id")
      const res = await axios.post(
        `http://localhost:8080/Tasks/${id}`,
        formData
      );

      if (res.status === 200) {
        alert("Task Added Successfully");
        console.log("Task Added Successfully", res.data);
        setFormData({
          title: "",
          description: "",
          status: "",
        });
        window.location.reload();
      }
    } catch (error) {
      console.log("Failed to add Data");
    }
  };

  return (
    <>
      <h2>Add Task</h2>

      <form className="add-task" id="form" onSubmit={handleSubmit}>
        {/* TITLE */}
        <input
          type="text"
          name="title"
          value={formData.title}
          placeholder="Title"
          onChange={handleChange}
          required
        />
        {/* DESCRIPTION */}
        <textarea
          name="description"
          value={formData.description}
          placeholder="Description"
          onChange={handleChange}
          cols="30"
          rows="5"
          required
        ></textarea>

        {/* STATUS */}
        <select
          name="status"
          value={formData.status}
          onChange={handleChange}
          required
        >
          <option value="">Select Status</option>
          <option value="Incomplete">Incomplete</option>
          <option value="Complete">Complete</option>
        </select>

        <button type="submit">Submit</button>
      </form>
    </>
  );
}

export default AddTask;
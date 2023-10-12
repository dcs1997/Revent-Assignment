import { useState, useEffect } from "react";
import axios from "axios";
import "./ViewTask.css";

function ViewTask() {
    const [task, setTask] = useState([]);
    const [page, setPage] = useState(0)
    let name = localStorage.getItem("name")
    let id = localStorage.getItem("id");
    useEffect(() => {
        fetchTask();
    }, [page]);

    const fetchTask = async () => {
        try {
            const res = await axios.get(
                `http://localhost:8080/Tasks/${id}/page?pageNumber=${page}`
            );
            console.log(res)
            if (res.status === 200) {
                setTask(res.data);
            } else {
                console.error("Failed to retrieve task");
            }
        } catch (error) {
            console.error(error.message);
        }
    };

    const handleDelete = async (tid) => {
        try {
            const res = await axios.delete(
                `http://localhost:8080/Tasks/${tid}`
            );

            if (res.status === 200) {
                setTask((prevTask) =>
                    prevTask.filter((task) => task.tid !== tid)
                );
            } else {
                console.error("Failed to delete task");
            }
        } catch (error) {
            console.error(error.message);
        }
    };
    const handleMarkComplete = async (tid) => {
        try {
            const data = { status: 'Complete' }
            const res = await axios.put(`http://localhost:8080/Tasks/${tid}`, data);
            console.log(res.data);

            if (res.status == 200) {
                const updatedData = res.data;

                const mainData = task.map((t) =>
                    t.tid == updatedData.tid ? updatedData : t
                )
                setTask(mainData);

            } else if (res.status == 400) {
                console.error("Task already marked as complete");
            } else {
                console.error("Failed to mark task as complete");
            }
        } catch (error) {
            console.error(error.message);
        }
    };



    return (
        <>
            <div>
                <h2>{name.toUpperCase()}'s Task</h2>
                <div id="task-container">
                    {task.map((task) => (
                        <div key={task.tid}>
                            <h3>Title: {task.title}</h3>
                            <p>Description: {task.description}</p>
                            <p>Status: {task.status}</p>
                            <button onClick={() => handleDelete(task.tid)}>Delete</button>
                            {task.status.toLowerCase() == "incomplete" ? <button onClick={() => handleMarkComplete(task.tid)}>Mark Complete</button> : <p>Completed</p>}
                        </div>
                    ))}
                </div>
            </div>
            <div id="pagination">
                <button
                    disabled={page == 0}
                    onClick={() => setPage(page - 1)}
                >
                    Previous
                </button>
                <button onClick={() => setPage(page + 1)}>Next</button>
            </div>
        </>


    );
}

export default ViewTask;
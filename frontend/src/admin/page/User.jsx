import React, {useEffect, useState} from "react";
import "../assets/css/argon-dashboard-tailwind.css";
import Sidebar from "../components/SideBar";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import axios from "axios";
import {API_BASE_URL} from "../../client/service/AuthService";

const User = () => {
    const [users, setUsers] = useState([]);
    const token = localStorage.getItem('token')
    useEffect(() => {
        axios.get(`${API_BASE_URL}/users/all`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        } )
            .then(response =>{
                setUsers(response.data.result || [])
            })
            .catch(error =>{
                console.error("Error fetching user: ", error);
                setUsers([]);

            })
    }, []);
    return (
        <div className="m-0 font-sans text-base antialiased font-normal dark:bg-slate-900 leading-default bg-gray-50 text-slate-500">
            <div className="absolute w-full bg-blue-500 dark:hidden min-h-75"></div>
                <Sidebar/>

            <main className="relative h-full max-h-screen transition-all duration-200 ease-in-out xl:ml-68 rounded-xl">
                <Navbar/>
                <div className="w-full px-6 py-6 mx-auto">
                    <div className="flex flex-wrap -mx-3">
                        <div className="flex-none w-full max-w-full px-3">
                            <div className="relative flex flex-col mb-6 bg-white shadow-xl dark:bg-slate-850 rounded-2xl">
                                <div className="p-6 pb-0 mb-0">
                                    <h6 className="dark:text-white">User</h6>
                                </div>
                                <div className="flex-auto px-0 pt-0 pb-2">
                                    <div className="overflow-x-auto">
                                        <table className="min-w-full divide-y divide-gray-200 text-slate-500">
                                            <thead className="bg-gray-50">
                                            <tr>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">ID</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">UserName</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Email</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">FullName</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Password</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Birthday</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Address</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Gender</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Phone</th>
                                                <th className="px-6 py-3 text-left font-medium text-gray-700">Active</th>
                                            </tr>
                                            </thead>
                                            <tbody className="bg-white divide-y divide-gray-200">
                                            {users.length === 0 ? (
                                                <tr>
                                                    <td colSpan={7} className="text-center py-4">No users found.</td>
                                                </tr>
                                            ) : (
                                                users.map(user => (
                                                    <tr key={user.id}>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.id}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.userName}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.email}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.fullName}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.password || "***"}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.birthday}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.address}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.gender}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">{user.phone}</td>
                                                        <td className="px-6 py-4 whitespace-nowrap">
                                                            <a href="#" className="text-xs text-slate-400">Edit</a>
                                                        </td>
                                                    </tr>
                                                ))
                                            )}
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <Footer/>
                </div>
            </main>
        </div>
    );
};

export default User;

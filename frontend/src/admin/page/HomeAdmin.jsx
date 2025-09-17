import React from "react";
import "../assets/css/argon-dashboard-tailwind.css";
import "../assets/css/nucleo-icons.css";
import "../assets/css/nucleo-svg.css";
import Sidebar from "../components/SideBar";
import Navbar from "../components/Navbar";

function CardRow() {
    // Card data could be put in an array and mapped for brevity
    return (
        <div className="flex flex-wrap -mx-3">
            {/* Card 1 */}
            <div className="w-full max-w-full px-3 mb-6 sm:w-1/2 sm:flex-none xl:mb-0 xl:w-1/4">
                <div
                    className="relative flex flex-col min-w-0 break-words bg-white shadow-xl dark:bg-slate-850 dark:shadow-dark-xl rounded-2xl bg-clip-border">
                    <div className="flex-auto p-4">
                        <div className="flex flex-row -mx-3">
                            <div className="flex-none w-2/3 max-w-full px-3">
                                <div>
                                    <p className="mb-0 font-sans text-sm font-semibold leading-normal uppercase dark:text-white dark:opacity-60">Today's
                                        Money</p>
                                    <h5 className="mb-2 font-bold dark:text-white">$53,000</h5>
                                    <p className="mb-0 dark:text-white dark:opacity-60">
                                        <span className="text-sm font-bold leading-normal text-emerald-500">+55%</span>
                                        since yesterday
                                    </p>
                                </div>
                            </div>
                            <div className="px-3 text-right basis-1/3">
                                <div
                                    className="inline-block w-12 h-12 text-center rounded-circle bg-gradient-to-tl from-blue-500 to-violet-500">
                                    <i className="ni leading-none ni-money-coins text-lg relative top-3.5 text-white"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Card 2 */}
            <div className="w-full max-w-full px-3 mb-6 sm:w-1/2 sm:flex-none xl:mb-0 xl:w-1/4">
                <div
                    className="relative flex flex-col min-w-0 break-words bg-white shadow-xl dark:bg-slate-850 dark:shadow-dark-xl rounded-2xl bg-clip-border">
                    <div className="flex-auto p-4">
                        <div className="flex flex-row -mx-3">
                            <div className="flex-none w-2/3 max-w-full px-3">
                                <div>
                                    <p className="mb-0 font-sans text-sm font-semibold leading-normal uppercase dark:text-white dark:opacity-60">Today's
                                        Users</p>
                                    <h5 className="mb-2 font-bold dark:text-white">2,300</h5>
                                    <p className="mb-0 dark:text-white dark:opacity-60">
                                        <span className="text-sm font-bold leading-normal text-emerald-500">+3%</span>
                                        since last week
                                    </p>
                                </div>
                            </div>
                            <div className="px-3 text-right basis-1/3">
                                <div
                                    className="inline-block w-12 h-12 text-center rounded-circle bg-gradient-to-tl from-red-600 to-orange-600">
                                    <i className="ni leading-none ni-world text-lg relative top-3.5 text-white"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Card 3 */}
            <div className="w-full max-w-full px-3 mb-6 sm:w-1/2 sm:flex-none xl:mb-0 xl:w-1/4">
                <div
                    className="relative flex flex-col min-w-0 break-words bg-white shadow-xl dark:bg-slate-850 dark:shadow-dark-xl rounded-2xl bg-clip-border">
                    <div className="flex-auto p-4">
                        <div className="flex flex-row -mx-3">
                            <div className="flex-none w-2/3 max-w-full px-3">
                                <div>
                                    <p className="mb-0 font-sans text-sm font-semibold leading-normal uppercase dark:text-white dark:opacity-60">New
                                        Clients</p>
                                    <h5 className="mb-2 font-bold dark:text-white">+3,462</h5>
                                    <p className="mb-0 dark:text-white dark:opacity-60">
                                        <span className="text-sm font-bold leading-normal text-red-600">-2%</span>
                                        since last quarter
                                    </p>
                                </div>
                            </div>
                            <div className="px-3 text-right basis-1/3">
                                <div
                                    className="inline-block w-12 h-12 text-center rounded-circle bg-gradient-to-tl from-emerald-500 to-teal-400">
                                    <i className="ni leading-none ni-paper-diploma text-lg relative top-3.5 text-white"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            {/* Card 4 */}
            <div className="w-full max-w-full px-3 sm:w-1/2 sm:flex-none xl:w-1/4">
                <div
                    className="relative flex flex-col min-w-0 break-words bg-white shadow-xl dark:bg-slate-850 dark:shadow-dark-xl rounded-2xl bg-clip-border">
                    <div className="flex-auto p-4">
                        <div className="flex flex-row -mx-3">
                            <div className="flex-none w-2/3 max-w-full px-3">
                                <div>
                                    <p className="mb-0 font-sans text-sm font-semibold leading-normal uppercase dark:text-white dark:opacity-60">Sales</p>
                                    <h5 className="mb-2 font-bold dark:text-white">$103,430</h5>
                                    <p className="mb-0 dark:text-white dark:opacity-60">
                                        <span className="text-sm font-bold leading-normal text-emerald-500">+5%</span>
                                        than last month
                                    </p>
                                </div>
                            </div>
                            <div className="px-3 text-right basis-1/3">
                                <div
                                    className="inline-block w-12 h-12 text-center rounded-circle bg-gradient-to-tl from-orange-500 to-yellow-500">
                                    <i className="ni leading-none ni-cart text-lg relative top-3.5 text-white"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

function Footer() {
    return (
        <footer className="pt-4">
            <div className="w-full px-6 mx-auto">
                <div className="flex flex-wrap items-center -mx-3 lg:justify-between">
                    <div className="w-full max-w-full px-3 mt-0 mb-6 shrink-0 lg:mb-0 lg:w-1/2 lg:flex-none">
                        <div className="text-sm leading-normal text-center text-slate-500 lg:text-left">
                            © {new Date().getFullYear()}, made with <i className="fa fa-heart"></i> by
                            <a href="https://www.creative-tim.com"
                               className="font-semibold text-slate-700 dark:text-white" target="_blank"
                               rel="noopener noreferrer">Creative Tim</a>
                            for a better web.
                        </div>
                    </div>
                    <div className="w-full max-w-full px-3 mt-0 shrink-0 lg:w-1/2 lg:flex-none">
                        <ul className="flex flex-wrap justify-center pl-0 mb-0 list-none lg:justify-end">
                            <li className="nav-item">
                                <a href="https://www.creative-tim.com"
                                   className="block px-4 pt-0 pb-1 text-sm font-normal transition-colors ease-in-out text-slate-500"
                                   target="_blank" rel="noopener noreferrer">Creative Tim</a>
                            </li>
                            <li className="nav-item">
                                <a href="https://www.creative-tim.com/presentation"
                                   className="block px-4 pt-0 pb-1 text-sm font-normal transition-colors ease-in-out text-slate-500"
                                   target="_blank" rel="noopener noreferrer">About Us</a>
                            </li>
                            <li className="nav-item">
                                <a href="https://creative-tim.com/blog"
                                   className="block px-4 pt-0 pb-1 text-sm font-normal transition-colors ease-in-out text-slate-500"
                                   target="_blank" rel="noopener noreferrer">Blog</a>
                            </li>
                            <li className="nav-item">
                                <a href="https://www.creative-tim.com/license"
                                   className="block px-4 pt-0 pb-1 pr-0 text-sm font-normal transition-colors ease-in-out text-slate-500"
                                   target="_blank" rel="noopener noreferrer">License</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    );
}

function HomeAdmin() {
    return (
        <div
            className="m-0 font-sans text-base antialiased font-normal dark:bg-slate-900 leading-default bg-gray-50 text-slate-500">
            <div className="absolute w-full bg-blue-500 dark:hidden min-h-75"></div>
            <Sidebar/>
            <main className="relative h-full max-h-screen transition-all duration-200 ease-in-out xl:ml-68 rounded-xl">
                <Navbar/>
                <div className="w-full px-6 py-6 mx-auto">
                    <CardRow/>
                    <Footer/>
                </div>
            </main>
        </div>
    );
}

export default HomeAdmin;
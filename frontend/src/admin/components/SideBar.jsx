import React from 'react';
// Nếu dùng Argon Dashboard Tailwind thì import CSS ở đây hoặc trong index.js/index.css của dự án
import '../assets/css/argon-dashboard-tailwind.css'; // Đổi lại đường dẫn nếu cần

function Sidebar() {
    return (
        <aside className="fixed inset-y-0 flex-wrap items-center justify-between block w-full p-0 my-4 overflow-y-auto antialiased transition-transform duration-200 -translate-x-full bg-white border-0 shadow-xl dark:shadow-none dark:bg-slate-850 max-w-64 ease-nav-brand z-990 xl:ml-6 rounded-2xl xl:left-0 xl:translate-x-0" aria-expanded="false">
            <div className="h-19">
                <i className="absolute top-0 right-0 p-4 opacity-50 cursor-pointer fas fa-times dark:text-white text-slate-400 xl:hidden" />
                <a className="block px-8 py-6 m-0 text-sm whitespace-nowrap dark:text-white text-slate-700"
                   href="https://demos.creative-tim.com/argon-dashboard-tailwind/pages/dashboard.html"
                   target="_blank"
                   rel="noopener noreferrer">
                    <img src="./assets/img/logo-ct-dark.png" className="inline h-full max-w-full transition-all duration-200 dark:hidden ease-nav-brand max-h-8" alt="main_logo" />
                    <img src="./assets/img/logo-ct.png" className="hidden h-full max-w-full transition-all duration-200 dark:inline ease-nav-brand max-h-8" alt="main_logo" />
                    <span className="ml-1 font-semibold transition-all duration-200 ease-nav-brand">Argon Dashboard 2</span>
                </a>
            </div>
            <hr className="h-px mt-0 bg-transparent bg-gradient-to-r from-transparent via-black/40 to-transparent dark:bg-gradient-to-r dark:from-transparent dark:via-white dark:to-transparent" />
            <div className="items-center block w-auto max-h-screen overflow-auto h-sidenav grow basis-full">
                <ul className="flex flex-col pl-0 mb-0">
                    {[
                        { label: "Dashboard", icon: "ni ni-tv-2", color: "text-blue-500", href: "/homeAdmin" },
                        { label: "User", icon: "ni ni-calendar-grid-58", color: "text-orange-500", href: "/user" },
                        { label: "Product", icon: "ni ni-credit-card", color: "text-emerald-500", href: "/product" },
                        { label: "Order", icon: "ni ni-app", color: "text-cyan-500", href: "/orderAdmin" },
                        { label: "RTL", icon: "ni ni-world-2", color: "text-red-600", href: "./pages/rtl.html" },
                    ].map((item, idx) => (
                        <li className="mt-0.5 w-full" key={item.label}>
                            <a className={`py-2.7 dark:text-white dark:opacity-80 text-sm ease-nav-brand my-0 mx-2 flex items-center whitespace-nowrap px-4 transition-colors ${idx === 0 ? "bg-blue-500/13 font-semibold text-slate-700 rounded-lg" : ""}`} href={item.href}>
                                <div className="mr-2 flex h-8 w-8 items-center justify-center rounded-lg bg-center stroke-0 text-center xl:p-2.5">
                                    <i className={`relative top-0 text-sm leading-normal ${item.color} ${item.icon}`}></i>
                                </div>
                                <span className="ml-1 duration-300 opacity-100 pointer-events-none ease">{item.label}</span>
                            </a>
                        </li>
                    ))}
                </ul>
            </div>
        </aside>
    );
}

export default Sidebar;
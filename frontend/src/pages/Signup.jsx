import { FaRegEnvelope } from 'react-icons/fa';
import { MdLockOutline } from 'react-icons/md';
import { FaUserAlt, FaPhoneAlt, FaHome } from 'react-icons/fa'; 
import { Link } from 'react-router-dom';

const Signup = () => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen py-2 bg-gray-100">
      <main className="flex flex-col items-center justify-center w-full flex-1 px-5 md:px-20 text-center">
        <div className="bg-white rounded-2xl shadow-2xl flex flex-col md:flex-row w-full md:w-2/3 max-w-4xl">
          <div className="w-full md:w-3/5 p-5">
            <div className="text-left font-bold">
              <span className="text-green-500">Apparel</span>Avenue
            </div>

            <div className="py-10">
              <h2 className="text-3xl font-bold text-green-500 mb-2">Create an Account</h2>
              <div className="border-2 w-10 border-green-500 inline-block mb-2"></div>

              <p className="text-gray-400 my-3">Use your details for registration</p>
              <div className="flex flex-col items-center">

                <div className="bg-gray-100 w-full md:w-64 p-2 flex items-center mb-3">
                  <FaUserAlt className="text-gray-400 mr-2" />
                  <input type="text" name="name" placeholder="Name" className="bg-gray-100 outline-none text-sm flex-1" />
                </div>

                <div className="bg-gray-100 w-full md:w-64 p-2 flex items-center mb-3">
                  <FaRegEnvelope className="text-gray-400 mr-2" />
                  <input type="email" name="email" placeholder="Email" className="bg-gray-100 outline-none text-sm flex-1" />
                </div>

                <div className="bg-gray-100 w-full md:w-64 p-2 flex items-center mb-3">
                  <FaPhoneAlt className="text-gray-400 mr-2" />
                  <input type="text" name="phone" placeholder="Phone Number" className="bg-gray-100 outline-none text-sm flex-1" />
                </div>

                <div className="bg-gray-100 w-full md:w-64 p-2 flex items-center mb-3">
                  <FaHome className="text-gray-400 mr-2" />
                  <input type="text" name="address" placeholder="Address" className="bg-gray-100 outline-none text-sm flex-1" />
                </div>

                <div className="bg-gray-100 w-full md:w-64 p-2 flex items-center mb-3">
                  <MdLockOutline className="text-gray-400 mr-2" />
                  <input type="password" name="password" placeholder="Password" className="bg-gray-100 outline-none text-sm flex-1" />
                </div>

                <div className="bg-gray-100 w-full md:w-64 p-2 flex items-center mb-3">
                  <MdLockOutline className="text-gray-400 mr-2" />
                  <input type="password" name="confirmPassword" placeholder="Confirm Password" className="bg-gray-100 outline-none text-sm flex-1" />
                </div>

                <a href="#" className="border-2 border-green-500 text-green-500 rounded-full px-12 py-2 inline-block font-semibold
                   hover:bg-green-500 hover:text-white transition-colors duration-300">Sign Up</a>
              </div>
            </div>
          </div>

          <div className="w-full md:w-2/5 bg-green-500 text-white rounded-b-2xl md:rounded-tr-2xl md:rounded-br-2xl py-10 md:py-36 px-12">
            <h2 className="text-3xl font-bold mb-2">Welcome Back!</h2>
            <div className="border-2 w-10 border-white inline-block mb-2"></div>
            <p className="mb-10">To keep connected with us, please login with your personal info.</p>
            <Link to="/login" className="border-2 border-white rounded-full px-12 py-2 inline-block font-semibold hover:bg-white hover:text-green-500 transition-colors duration-300">Sign In</Link>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Signup;
import { Routes, Route } from 'react-router-dom'
import Header from './components/Header'
import PublicNav from './components/PublicNav'
import Navbar from './components/Navbar'
import ProtectedRoute from './components/ProtectedRoute'
import Welcome from './pages/Welcome'
import About from './pages/About'
import Departments from './pages/Departments'
import Contact from './pages/Contact'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import Students from './pages/Students'
import Courses from './pages/Courses'
import Faculty from './pages/Faculty'
import Announcements from './pages/Announcements'
import Toppers from './pages/Toppers'
import PlacedStudents from './pages/PlacedStudents'
import Complaints from './pages/Complaints'
import Register from './pages/Register'
import User from './pages/User'
import Footer from './components/Footer'
import { Privacy,Terms,Sitemap } from './pages/Legal'
import FloatingPanel from './components/FloatingPanel'
import StudyMaterials from './pages/StudyMaterials'
import ChangePassword from './pages/ChangePassword'

function App() {
  return (
    <div>
      <Header />
      <FloatingPanel/>
      <PublicNav />
      <div className="app-body">
        <Navbar />
        <main className="app-main">
          <Routes>
            <Route path="/" element={<Welcome />} />
            <Route path="/about" element={<About />} />
            <Route path="/departments" element={<Departments />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/login" element={<Login />} />
            <Route path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />
            <Route path="/students" element={<ProtectedRoute><Students /></ProtectedRoute>} />
            <Route path="/courses" element={<ProtectedRoute><Courses /></ProtectedRoute>} />
            <Route path="/faculty" element={<ProtectedRoute><Faculty /></ProtectedRoute>} />
            <Route path="/announcements" element={<ProtectedRoute><Announcements /></ProtectedRoute>} />
            <Route path="/toppers" element={<ProtectedRoute><Toppers /></ProtectedRoute>} />
            <Route path="/placements" element={<ProtectedRoute><PlacedStudents /></ProtectedRoute>} />
            <Route path="/complaints" element={<ProtectedRoute><Complaints /></ProtectedRoute>} />
            <Route path="/register" element={<ProtectedRoute><Register /></ProtectedRoute>} />
            <Route path="/users" element={<ProtectedRoute><User /></ProtectedRoute>} />
            <Route path="/privacy" element={<Privacy />} />
            <Route path="/terms" element={<Terms />} />
            <Route path="/sitemap" element={<Sitemap />} />
            <Route path="/materials" element={<ProtectedRoute><StudyMaterials /></ProtectedRoute>} />      
            <Route path="/change-password" element={<ProtectedRoute><ChangePassword /></ProtectedRoute>} />   
          </Routes>
        </main>
      </div>
      <Footer />
    </div>
  )
}
export default App
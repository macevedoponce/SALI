-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-11-2022 a las 23:32:27
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sali`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id` int(11) NOT NULL,
  `aluNombre` varchar(50) NOT NULL,
  `aluApellidoPaterno` varchar(50) NOT NULL,
  `aluApellidoMaterno` varchar(50) NOT NULL,
  `aluDni` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id`, `aluNombre`, `aluApellidoPaterno`, `aluApellidoMaterno`, `aluDni`) VALUES
(1, 'Valeria Camila', 'CHAVEZ', 'DELGADILLO', '12345678'),
(2, 'Thiago Jefferson', 'SOTO', 'ESPINOZA', '98765432'),
(3, 'Jimena Naida', 'SUMAYLLO', 'RODRIGUEZ', '14785236'),
(4, 'Amy Nicole', 'AVELLANEDA', 'AROTOMA', '25896314'),
(5, 'Tiago Dereck', 'AVELLANEDA', 'SALVATIERRA', '14253678'),
(6, 'Gadiel Daryl', 'AVELLANEDA', 'FLORES', '74415285'),
(7, 'Sebastian Augusto', 'CHAVEZ', 'CHUA', '78541254'),
(8, 'Lian Angel', 'CHAVEZ', 'ASIS', '10245012'),
(9, 'Adolfh Daryl', 'PEREZ', 'INGA', '11245578'),
(10, 'Mathias Ian', 'CHAVEZ', 'TINOCO', '11423568'),
(11, 'Alejandro Sebastian', 'CHAVEZ', 'MANTARI', '66332255'),
(12, 'Cristhian Mathias', 'PEREZ', 'AGUIRRE', '13132424'),
(13, 'Mark Raul', 'CHAVEZ', 'VILCHEZ', '25588552'),
(14, 'Sebastian Angel', 'BENITO', 'VILCHEZ', '68989885'),
(15, 'Cristhian Camilo', 'PEREZ', 'TUPACYUPANQUI', '14256978');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE `asistencia` (
  `id` int(11) NOT NULL,
  `alumno_id` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentos`
--

CREATE TABLE `cuentos` (
  `id` int(11) NOT NULL,
  `cuento` text NOT NULL,
  `alumno_id` int(11) NOT NULL,
  `imagen_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuentos`
--

INSERT INTO `cuentos` (`id`, `cuento`, `alumno_id`, `imagen_id`) VALUES
(1, 'Este es un hombre enojado porque no quiere ver a su esposa', 1, 1),
(2, 'Esta es la historia de un niño triste porque no tiene nada que comer además no sabe tocar el violín', 1, 1),
(5, 'en esta imagen se puede apreciar una persona que está haciendo claramente molestada por alguien y no está como', 1, 1),
(6, 'esta imagen se puede apreciar en la mujer que claramente se encuentra triste en estado de depresión y se encuentra llorando por causas que no puedo comprender', 1, 1),
(7, 'y dictarle no por ejemplo en esta imagen se ve claramente que el niño está sentado y posiblemente tiene hambre', 1, 1),
(8, 'en este apartado se puede apreciar a una persona que está triste', 1, 1),
(9, 'un dos tres un dos tres', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reto_foto_diaria`
--

CREATE TABLE `reto_foto_diaria` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `alumno_id` int(11) NOT NULL,
  `alumno_Foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tree_paint`
--

CREATE TABLE `tree_paint` (
  `id` int(11) NOT NULL,
  `imagen_url` text NOT NULL,
  `alumno_id` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tree_paint`
--

INSERT INTO `tree_paint` (`id`, `imagen_url`, `alumno_id`, `fecha`) VALUES
(2, 'http://192.168.1.46/saliApi/imagenes/20221115_185723.png', 1, '2022-11-16'),
(4, 'http://192.168.1.46/saliApi/imagenes/20221115_191413.png', 1, '2022-11-16'),
(5, 'http://192.168.1.46/saliApi/imagenes/20221115_191711.png', 1, '2022-11-16'),
(6, 'http://192.168.1.46/saliApi/imagenes/.png', 1, '2022-11-17'),
(7, 'http://192.168.1.46/saliApi/imagenes/20221116_235925.png', 1, '2022-11-17'),
(8, 'http://192.168.1.46/saliApi/imagenes/20221116_193112.png', 1, '2022-11-17'),
(9, 'http://192.168.1.46/saliApi/imagenes/20221117_010128.png', 1, '2022-11-17'),
(10, 'http://192.168.1.46/saliApi/imagenes/20221117_011849.png', 1, '2022-11-17'),
(11, 'http://192.168.1.46/saliApi/imagenes/20221117_012640.png', 1, '2022-11-17');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cuentos`
--
ALTER TABLE `cuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reto_foto_diaria`
--
ALTER TABLE `reto_foto_diaria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tree_paint`
--
ALTER TABLE `tree_paint`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuentos`
--
ALTER TABLE `cuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `reto_foto_diaria`
--
ALTER TABLE `reto_foto_diaria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tree_paint`
--
ALTER TABLE `tree_paint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

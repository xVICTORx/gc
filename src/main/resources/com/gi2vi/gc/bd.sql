-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 20-01-2012 a las 19:57:24
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de datos: `gc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE IF NOT EXISTS `area` (
  `idArea` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `abreviatura` varchar(45) NOT NULL,
  `descripcion` text,
  `estatus` enum('ACTIVO','INACTIVO') NOT NULL,
  `idDependencia` int(11) NOT NULL,
  PRIMARY KEY (`idArea`),
  KEY `fk_area_dependencia1` (`idDependencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area_empleado`
--

CREATE TABLE IF NOT EXISTS `area_empleado` (
  `idArea` int(11) NOT NULL,
  `idEmpleado` int(11) NOT NULL,
  PRIMARY KEY (`idArea`,`idEmpleado`),
  KEY `fk_Area_has_Empleado_Empleado1` (`idEmpleado`),
  KEY `fk_Area_has_Empleado_Area1` (`idArea`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dependencia`
--

CREATE TABLE IF NOT EXISTS `dependencia` (
  `idDependencia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `telefono` varchar(200) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `cp` varchar(5) NOT NULL,
  `municipio` varchar(70) NOT NULL,
  `idEstado` int(11) NOT NULL,
  PRIMARY KEY (`idDependencia`),
  KEY `fk_Dependencia_Estado1` (`idEstado`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE IF NOT EXISTS `empleado` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `idPuesto` int(11) DEFAULT NULL,
  `idTelefono` int(11) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidoPaterno` varchar(100) NOT NULL,
  `apellidoMaterno` varchar(100) NOT NULL,
  `correo` varchar(250) NOT NULL,
  `estatus` enum('ACTIVO','INACTIVO') NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `fk_Empleado_Puesto` (`idPuesto`),
  KEY `fk_Empleado_Extencion1` (`idTelefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE IF NOT EXISTS `estado` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) NOT NULL,
  `clave` varchar(10) NOT NULL,
  `idPais` int(11) NOT NULL,
  PRIMARY KEY (`idEstado`),
  KEY `fk_Estado_Pais1` (`idPais`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estatus_volante`
--

CREATE TABLE IF NOT EXISTS `estatus_volante` (
  `idEstatusVolante` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idEstatusVolante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log_volante_estatus`
--

CREATE TABLE IF NOT EXISTS `log_volante_estatus` (
  `idVolante` int(11) NOT NULL AUTO_INCREMENT,
  `idEstatusVolante` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idVolante`,`idEstatusVolante`),
  KEY `fk_Volante_has_Estatus_Estatus1` (`idEstatusVolante`),
  KEY `fk_Volante_has_Estatus_Volante1` (`idVolante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE IF NOT EXISTS `pais` (
  `idPais` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) NOT NULL,
  `clave` varchar(10) NOT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puesto`
--

CREATE TABLE IF NOT EXISTS `puesto` (
  `idPuesto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `descripcion` text,
  `estatus` enum('ACTIVO','INACTIVO') NOT NULL,
  PRIMARY KEY (`idPuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE IF NOT EXISTS `servicio` (
  `idServicio` int(11) NOT NULL AUTO_INCREMENT,
  `idArea` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `tiempo` int(11) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `estatus` enum('ACTIVO','INACTIVO') NOT NULL,
  PRIMARY KEY (`idServicio`),
  KEY `fk_Servicio_Area1` (`idArea`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono`
--

CREATE TABLE IF NOT EXISTS `telefono` (
  `idtelefono` int(11) NOT NULL AUTO_INCREMENT,
  `telefono` varchar(45) NOT NULL,
  `extension` varchar(45) NOT NULL,
  PRIMARY KEY (`idtelefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `volante`
--

CREATE TABLE IF NOT EXISTS `volante` (
  `idVolante` int(11) NOT NULL AUTO_INCREMENT,
  `idServicio` int(11) NOT NULL,
  `tiempoProrroga` int(20) NOT NULL,
  `tiempo` int(20) NOT NULL,
  `documento` varchar(250) DEFAULT NULL,
  `idEstatusVolante` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idEmpleadoCreador` int(11) NOT NULL,
  `idEmpleadoJefe` int(11) NOT NULL,
  `idEmpleadoAsignado` int(11) NOT NULL,
  PRIMARY KEY (`idVolante`),
  KEY `fk_Volante_Estatus1` (`idEstatusVolante`),
  KEY `fk_volante_servicio1` (`idServicio`),
  KEY `fk_volante_empleado1` (`idEmpleadoCreador`),
  KEY `fk_volante_empleado2` (`idEmpleadoJefe`),
  KEY `fk_volante_empleado3` (`idEmpleadoAsignado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `area`
--
ALTER TABLE `area`
  ADD CONSTRAINT `fk_area_dependencia1` FOREIGN KEY (`idDependencia`) REFERENCES `dependencia` (`idDependencia`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `area_empleado`
--
ALTER TABLE `area_empleado`
  ADD CONSTRAINT `fk_Area_has_Empleado_Area1` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Area_has_Empleado_Empleado1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `dependencia`
--
ALTER TABLE `dependencia`
  ADD CONSTRAINT `fk_Dependencia_Estado1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_Empleado_Extencion1` FOREIGN KEY (`idTelefono`) REFERENCES `telefono` (`idtelefono`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Empleado_Puesto` FOREIGN KEY (`idPuesto`) REFERENCES `puesto` (`idPuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `estado`
--
ALTER TABLE `estado`
  ADD CONSTRAINT `fk_Estado_Pais1` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idPais`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `log_volante_estatus`
--
ALTER TABLE `log_volante_estatus`
  ADD CONSTRAINT `fk_Volante_has_Estatus_Estatus1` FOREIGN KEY (`idEstatusVolante`) REFERENCES `estatus_volante` (`idEstatusVolante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Volante_has_Estatus_Volante1` FOREIGN KEY (`idVolante`) REFERENCES `volante` (`idVolante`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `fk_Servicio_Area1` FOREIGN KEY (`idArea`) REFERENCES `area` (`idArea`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `volante`
--
ALTER TABLE `volante`
  ADD CONSTRAINT `fk_volante_empleado1` FOREIGN KEY (`idEmpleadoCreador`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_volante_empleado2` FOREIGN KEY (`idEmpleadoJefe`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_volante_empleado3` FOREIGN KEY (`idEmpleadoAsignado`) REFERENCES `empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Volante_Estatus1` FOREIGN KEY (`idEstatusVolante`) REFERENCES `estatus_volante` (`idEstatusVolante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_volante_servicio1` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

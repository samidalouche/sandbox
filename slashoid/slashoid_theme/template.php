<?php

function _phptemplate_variables($hook, $vars) {

  global $user;
  $node=$vars['node'];
  
  switch($hook) {

    case 'page' :

    case 'node' :

    break;
  } // END if the switch

  return $vars;

}

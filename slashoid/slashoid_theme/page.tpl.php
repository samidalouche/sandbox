<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="<?php print $language ?>" xml:lang="<?php print $language ?>">

<head>
  <title><?php print $head_title; ?></title>
  <?php print $head; ?>
  <?php print $styles; ?>
  <?php print $scripts; ?>

  <!-- REMOVE these if you are using jquery-update!! -->
  <script type="text/javascript" src="<?php print $base_path ?>misc/collapse-fix.js"></script>
  <script type="text/javascript" src="<?php print $base_path ?>misc/compat-1.0.js"></script>

</head>

<body>
  
  <div id="container">
    
    <div id="header">
      <div id="header_title">

        <?php if (isset($primary_links)) : ?>
          <?php print theme('links', $primary_links, array('class' => 'links primary-links')) ?>
        <?php endif; ?>
        
        <div id="header_search">
          <?php print $search_box; ?>
        </div>

        <?php if (!empty($site_name)): ?>
          <h1><a href="<?php print $base_path ?>" title="<?php print t('Home'); ?>"><?php print $site_name; ?></a></h1>
        <?php endif; ?>
        
        <?php if (!empty($site_slogan)): ?>
          <p><?php print $site_slogan; ?></p>
        <?php endif; ?>

      </div>
    
      <?php if (module_exists('drigg')) { ?>
        <div id="header_categories">
          <?php
            if (module_exists('drigg')) {
              print drigg_ui_submit_button();
            }
          ?>
          <?php 
          print drigg_ui_sections(FALSE,0); 
          ?>
        </div>
         
        <?php 
          $subcates = drigg_ui_sections(FALSE,1);
        ?>
        <?php if ($subcates) { ?>
          <div id="header_subcategories">
            <?php print $subcates; ?>
          </div>
        <?php } ?>

        <?php 
          $sub2cates = drigg_ui_sections(FALSE,2);
        ?>
        <?php if ($sub2cates) { ?>
          <div id="header_subcategories2">
            <?php print $sub2cates; ?>
          </div>
        <?php } ?>


      <?php } ?>
      
      <?php if (!empty($breadcrumb)): ?>
        <div id="header_breadcrumb">
          <?php print $breadcrumb; ?>
        </div>
      <?php endif; ?>
      
      <?php if (!empty($header)): ?>
        <div id="header_content">
          <?php print $header; ?>
        </div>
      <?php endif; ?>
    </div> <!-- /header -->
    
    <div id="content">
      <?php if( $show_ads && ! ( arg(0) == 'node' && is_numeric(arg(1)) ) ) { ?>
        <div class="googleads_top">
          <!-- Google ad here. 468x60 works... -->
        </div>
      <?php } ?>

      <div id="content_center" class="column">
        <div class="controlbar clearfix">
          <?php if (!empty($title) && !( arg(0) == 'node' && is_numeric(arg(1)))) : ?>
            <h1><?php print $title; ?></h1>
          <?php endif; ?>
          <?php
            if ($page == 0 && module_exists('drigg')) {
              print drigg_ui_type_menu();
              print drigg_ui_order_menu();
            }
          ?>
        </div>
        
        <?php if (!empty($tabs)): ?>
          <div class="tabs"><?php print $tabs; ?></div>
        <?php endif; ?>
        
        <?php print $help; ?>
        <?php print $messages; ?>
        <?php print $content; ?>
      </div> <!-- /content_center -->
      
      <?php if (!empty($sidebar_left)): ?>
        <div id="content_left" class="column">
          <?php print $sidebar_left; ?>
        </div> <!-- /content_left -->
      <?php endif; ?>

      <?php if (!empty($sidebar_right)): ?>
        <div id="content_right" class="column">

          <?php print $sidebar_right; ?>

          <?php if( $show_ads) { ?>
            <div class="googleads_left">
              <!-- Google ad here. 160x600 works... -->
            </div>
          <?php } ?>
        </div> <!-- /content_right -->
      <?php endif; ?>

    </div> <!-- /content -->

    <div id="footer">
      <?php print $footer_message; ?>
      <a href="http://www.drigg-code.org"><img src="<?php print path_to_theme()?>/img/drigg_80_15.gif" alt="Drigg" /></a>
    </div> <!-- /footer -->

    <?php print $closure; ?>

    
  </div> <!-- /container -->

</body>
</html>

<div class="node ntype-<?php print $node->type; ?> <?php print $zebra; ?> clearfix" id="node-<?php print $node->nid; ?>">
  <?php print extra_voting_forms_show_form($node,'n',3); ?>
    <?php $embedded_stuff=drigg_embed_contents($node,$node_url,$teaser); ?>

  <div class="offset">
    <?php if($teaser){ ?>
      <h2><a href="<?php print $node_url ?>"><?php print $title; ?></a></h2>
    <?php } else { ?>
     
      <!-- This accommodates for the fact that Drigg can be configured so that
           a story doesn't have a link pointing to an external resource --> 
      <?php if( drigg_url_is_local($node) ){ ?>
        <h2><?php print $title; ?></h2>
      <?php } else {?>
         <h2>
           <a href="<?php print drigg_link($node) ?>"><?php print $title; ?></a>
         </h2>
      <?php } ?>

    <?php } ?>
    
    <?php # Change 'tag' into 'category' to show a category link instead ?>
    <?php $section_link=drigg_section_link($node,'category'); ?>

    <?php if ($teaser && $embedded_stuff) { ?>
      <div class="embedded-teaser">
        <?php print $embedded_stuff ?>
      </div>
    <?php } ?>

    <div class="story">

      <?php if (! drigg_url_is_local($node) ){ ?>
        <small><?php print theme_format_url_home($url); ?> &ndash;</small>
      <?php } ?>

      <?php print $content; ?>
      <?php if($teaser){ ?>
        <a href="<?php print $node_url ?>"><?php print t('Read more &raquo;'); ?></a>
      <?php } else { ?>

        <?php if (! drigg_url_is_local($node) ){ ?>
          <a href="<?php print $node->url ?>"><?php print t('Read &raquo;'); ?></a>
        <?php } ?>


      <?php } ?>
    </div>

    <?php if (!$teaser && $embedded_stuff) { ?>
      <div class="embedded">
        <?php print $embedded_stuff ?>
      </div>
    <?php } ?>

    <?php if($links){ ?>
      <div class="storydata">
        <?php print theme('user_picture',user_load(array('uid'=>$node->uid)));?>
        <?php if($submitted) print t('Created by ').theme('username', $node);?> 
        <?php print drigg_created_string($node); ?>
        <?php $ps=drigg_promoted_string($node); ?>
        <?php if($ps){ ?>
        &ndash; <strong><?php print $ps; ?></strong>
        <?php } ?>
        <br />
        Category: <?php print $section_link; ?> &nbsp; Tags: <?php print $terms; ?>
      </div>
      
      <div class="links">
        <?php print $links; ?>
      </div>
    <?php } ?>
    
  </div>
</div>

<?php if(!$teaser):  ?>
  <div class="node-related clearfix">

    <?php if (module_exists('drigg_rl')){ ?>
      <h2>Similar stories</h2>
      <?php print drigg_rl_related_links_get_html($node->title.' '.$node->body, $node->nid, 5, FALSE); ?>
    <?php } ?>

  </div>
<?php endif; ?>

<?php print drigg_ui_article_menu(); ?>

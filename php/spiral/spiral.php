<?php
class Spiral {

  // in our case spiral moves in following direction right->bottom->left->top
  private $directions = array (
    array ('x' =>  1, 'y' =>  0),
    array ('x' =>  0, 'y' =>  1),
    array ('x' => -1, 'y' =>  0),
    array ('x' =>  0, 'y' => -1),
  );

  public function __construct($two_dim_array) {
    $this->validate_array($two_dim_array);
    $this->two_dim_array = $two_dim_array;
  }

  public function output_spiral() {
    return implode(', ', $this->ordered());
  }

  public function ordered() {
    // using recursion to move over all array elements
    return $this->spiral_collect_step($this->two_dim_array, 0, 0, $this->directions[0], array());
  }

  private function validate_array($arr) {
    //let's check if we are dealing with normal two dimensional array
    if (empty($arr)) throw new Exception('Empty array');
    if (is_array($arr[0])) $elements_in_row = count($arr[0]);
    foreach ($arr as $row) {
      if(!is_array($row)) {
        throw new Exception('Invalid two dimensional array');
      }
      if(count($row) != $elements_in_row) {
        throw new Exception('Array with different rows size');
      }
    }
  }

  private function spiral_collect_step($two_dim_array, $x, $y, $direction, $spiral, $visited_cells = array()) {
    array_push($spiral, $two_dim_array[$y][$x]);
    if($this->should_switch_direction($two_dim_array, $x, $y, $direction, $visited_cells)) {
      $direction = $this->get_new_direction($direction);
    }

    array_push($visited_cells, array($x, $y));
    $x += $direction['x'];
    $y += $direction['y'];

    if(count($visited_cells) >= $this->elements_in_two_dim_array($two_dim_array)) {
      return $spiral;
    } else {
      return $this->spiral_collect_step($two_dim_array, $x, $y, $direction, $spiral, $visited_cells);
    }
  }

  private function should_switch_direction($arr, $x, $y, $direction, $visited_cells) {
    return 
    $direction['x'] ==  1 && $x == count($arr[0]) - 1 ||
    $direction['x'] == -1 && $x == 0 ||
    $direction['y'] ==  1 && $y == count($arr) - 1 ||
    $direction['y'] == -1 && $y == 0 ||
    // we don't want to visit cell we already have been on
    !$this->next_cell_is_new($visited_cells, $x, $y, $direction);
  }

  private function next_cell_is_new($visited_cells, $x, $y, $direction) {
    return !in_array(array($x + $direction['x'], $y + $direction['y']), $visited_cells);
  }

  private function get_new_direction($current_direction) {
    $current_index = array_search($current_direction , $this->directions);
    $current_index >= count($this->directions) - 1 ? $new_index = 0 : $new_index = $current_index + 1;
    return $this->directions[$new_index]; 
  }

  private function elements_in_two_dim_array($arr) {
    $elements_sum = 0;
    foreach ($arr as $row) {
      $elements_sum += count($row);
    }
    return $elements_sum;
  }

}
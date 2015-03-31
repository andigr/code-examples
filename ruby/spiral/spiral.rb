class Spiral

  # in our case spiral moves in following direction right->bottom->left->top
  DIRECTIONS = [
    { x: 1, y: 0 }, #to right
    { x: 0, y: 1 }, #to bottom
    { x:-1, y: 0 }, #to left
    { x: 0, y:-1 }  #to top
  ]

  def initialize two_dim_array
    validate_array two_dim_array
    @two_dim_array = two_dim_array
  end

  def ordered
    # using recursion to move over all array elements
    spiral_collect_step @two_dim_array, 0, 0, DIRECTIONS[0], []
  end

  private

  def validate_array arr
    # let's check if we are dealing with normal two dimensional array
    raise "Empty array" if arr.empty?
    elements_in_row = arr[0].length if arr[0].respond_to?(:length)
    arr.each do |row|
      unless row.kind_of?(Array)
        raise "Invalid two dimensional array"
      end
      if row.length != elements_in_row
        raise "Array with different rows size"
      end
    end
  end

  def spiral_collect_step two_dim_array, x, y, direction, spiral, visited_cells = []
    spiral << two_dim_array[y][x]
    if should_switch_direction? two_dim_array, x, y, direction, visited_cells
      direction = get_new_direction direction
    end

    visited_cells << [x, y]
    x += direction[:x]
    y += direction[:y]

    if visited_cells.length >= elements_in_two_dim_array(two_dim_array)
      return spiral
    else
      spiral_collect_step two_dim_array, x, y, direction, spiral, visited_cells
    end
  end

  def next_cell_is_new? visited_cells, x, y, direction
    !visited_cells.include? [x + direction[:x], y + direction[:y]]
  end

  def should_switch_direction? arr, x, y, direction, visited_cells
    direction[:x] == 1 && x == arr[0].length - 1 ||
    direction[:x] == -1 && x == 0 ||
    direction[:y] == 1 && y == arr.length - 1 ||
    direction[:y] == -1 && y == 0 ||
    # we don't want to visit cell we already have been on
    !next_cell_is_new?(visited_cells, x, y, direction)
  end

  def get_new_direction current_direction
    current_index = DIRECTIONS.index(current_direction)
    current_index >= DIRECTIONS.length - 1 ? new_index = 0 : new_index = current_index + 1
    DIRECTIONS[new_index]
  end

  def elements_in_two_dim_array arr
    arr.inject(0) { |elements_number, row| elements_number += row.length }
  end

end
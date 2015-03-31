<?php
require_once('spiral.php');

class SpiralTest extends PHPUnit_Framework_TestCase {
    
    public function test_3_3_matrix() {
        $array = array(
          array('1','2','3'),
          array('4','5','6'),
          array('7','8','9'),
        );
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
        $expected = '1, 2, 3, 6, 9, 8, 7, 4, 5'; 
        $this->assertEquals($expected, $actual);
    }

    public function test_4_4_matrix() {
        $array = array(
          array( '1',  '2',  '3',  '4'),
          array( '5',  '6',  '7',  '8'),
          array( '9', '10', '11', '12'),
          array('13', '14', '15', '16'),
        );
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
        $expected = '1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10'; 
        $this->assertEquals($expected, $actual);
    }

    public function test_3_4_matrix() {
        $array = array(
          array( '1',  '2',  '3'),
          array( '4',  '5',  '6'),
          array( '7',  '8',  '9'),
          array('10', '11', '12'),
        );
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
        $expected = '1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8'; 
        $this->assertEquals($expected, $actual);
    }

    public function test_small_matrix() {
        $array = array(
          array('1')
        );
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
        $expected = '1'; 
        $this->assertEquals($expected, $actual);
    }

    public function test_one_column_matrix() {
        $array = array(
          array('1'),
          array('2'),
          array('3'),
        );
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
        $expected = '1, 2, 3'; 
        $this->assertEquals($expected, $actual);
    }

    public function test_one_dimension_array_exception() {
        $this->setExpectedException(
          'Exception', 'Invalid two dimensional array'
        );
        $array = array(1, 2, 3);
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
    }

    public function test_empty_array_exception() {
        $this->setExpectedException(
          'Exception', 'Empty array'
        );
        $array = array();
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
    }

    public function test_different_rows_size_exception() {
        $this->setExpectedException(
          'Exception', 'Array with different rows size'
        );
        $array = array(
            array(1, 2, 3),
            array(4, 5),
            array(6, 7, 8)
        );
        $spiral = new Spiral($array);
        $actual = $spiral->output_spiral();
    }
}
?>
load 'spiral.rb'

describe Spiral do

  context 'matrix ordering' do

    it 'should order 3x3 matrix' do
      spiral = Spiral.new [
        [1,2,3],
        [4,5,6],
        [7,8,9]
      ]
      spiral.ordered.should == [1,2,3,6,9,8,7,4,5]
    end

    it 'should order 4x4 matrix' do
      spiral = Spiral.new [
        [ 1, 2, 3, 4],
        [ 5, 6, 7, 8],
        [ 9,10,11,12],
        [13,14,15,16]
      ]
      spiral.ordered.should == [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
    end

    it 'should order 3x4 matrix' do
      spiral = Spiral.new [
        [ 1, 2, 3],
        [ 4, 5, 6],
        [ 7, 8, 9],
        [10,11,12]
      ]
      spiral.ordered.should == [1,2,3,6,9,12,11,10,7,4,5,8]
    end

    it 'should order smallest non empty matrix' do
      spiral = Spiral.new [
        [ 1]
      ]
      spiral.ordered.should == [1]
    end

    it 'should order one-column matrix' do
      spiral = Spiral.new [
        [ 1],
        [ 2],
        [ 3]
      ]
      spiral.ordered.should == [1, 2, 3]
    end

    it 'should raise exception for one dimensional array' do
      expect{ Spiral.new [1,2,3] }.to raise_error "Invalid two dimensional array"
    end

    it 'should raise exception for empty array' do
      expect{ Spiral.new [] }.to raise_error "Empty array"
    end

    it 'should raise exception for array with rows of different size' do
      array = [
        [1,2,3],
        [4,5],
        [7,8,9]
      ]
      expect{ Spiral.new array }.to raise_error "Array with different rows size"
    end

  end

end

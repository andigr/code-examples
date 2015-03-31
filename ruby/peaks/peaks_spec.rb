load 'peaks.rb'

describe Peaks do

  context 'looking for max amount of same size block with peaks we can divide array into' do

    it 'should determine three blocks with peaks' do
      peak = Peaks.new [1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2]
      peak.can_be_divided_into.should == 3
    end

    it 'should determine three blocks with peaks' do
      peak = Peaks.new [1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1]
      peak.can_be_divided_into.should == 4
    end

    it 'should determine one block with peaks' do
      peak = Peaks.new [1, 2, 3, 4, 5, 6, 7]
      peak.can_be_divided_into.should == 0
    end

    it 'should determine one part for array with less than 2 elements' do
      peak = Peaks.new [1, 2]
      peak.can_be_divided_into.should == 0
    end

    it 'should return 0 for empty array' do
      peak = Peaks.new []
      peak.can_be_divided_into.should == 0
    end

  end

end
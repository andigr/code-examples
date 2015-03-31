load 'common_prime_divisors.rb'

describe CommonPrimeDivisors do

  context 'common prime divisors' do

    it 'it should find common prime divisors for same numbers' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(7, 7).should == true
    end

    it 'it should find common prime divisors for 4 and 8' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(4, 8).should == true
    end

    it 'it should find common prime divisors for 28 and 56' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(28, 56).should == true
    end

    it 'it should find common prime divisors for 15 and 75' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(15, 75).should == true
    end

    it 'it should not find same prime divisors for 10 and 30' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(10, 30).should == false
    end

    it 'it should not find same prime divisors for 9 and 5' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(9, 5).should == false
    end

    it 'it should not find same prime divisors for 28 and 35 (2, 7 vs 7)' do
      CommonPrimeDivisors.new.has_common_prime_divisors?(28, 35).should == false
    end

  end

end